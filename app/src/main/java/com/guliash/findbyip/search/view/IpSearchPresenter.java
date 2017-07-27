package com.guliash.findbyip.search.view;

import com.guliash.findbyip.core.mvp.BasePresenter;
import com.guliash.findbyip.search.ip.IpInfoService;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IpSearchPresenter extends BasePresenter<IpSearchView> {

    private final static int FIND_DEBOUNCE_INTERVAL_SECONDS = 1;
    private final IpInfoService ipInfoService;

    @Inject
    public IpSearchPresenter(IpInfoService ipInfoService) {
        this.ipInfoService = ipInfoService;
    }

    @Override
    public void bind(IpSearchView view) {
        super.bind(view);

        unsubscribeOnUnbind(
                view.findByIpSelections()
                        .toFlowable(BackpressureStrategy.DROP)
                        .debounce(FIND_DEBOUNCE_INTERVAL_SECONDS, TimeUnit.SECONDS)
                        .flatMap(
                                ø -> ipInfoService.findByIp(view.ip()).subscribeOn(Schedulers.io())
                        )
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(ø -> view.showError())
                        .retryWhen(throwables -> throwables)
                        .subscribe(System.out::println)
        );
    }
}
