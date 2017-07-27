package com.guliash.findbyip.search.view;

import android.support.annotation.NonNull;

import com.guliash.findbyip.core.mvp.BasePresenter;
import com.guliash.findbyip.core.rx.ComputationScheduler;
import com.guliash.findbyip.core.rx.IoScheduler;
import com.guliash.findbyip.core.rx.MainScheduler;
import com.guliash.findbyip.search.ip.IpInfoService;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Scheduler;

public class IpSearchPresenter extends BasePresenter<IpSearchView> {

    private final static int FIND_DEBOUNCE_INTERVAL_SECONDS = 1;
    private final IpInfoService ipInfoService;
    private final Scheduler mainScheduler;
    private final Scheduler ioScheduler;
    private final Scheduler computationScheduler;

    @Inject
    public IpSearchPresenter(@NonNull IpInfoService ipInfoService,
                             @NonNull @MainScheduler Scheduler mainScheduler,
                             @NonNull @IoScheduler Scheduler ioScheduler,
                             @NonNull @ComputationScheduler Scheduler computationScheduler) {
        this.ipInfoService = ipInfoService;
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void bind(IpSearchView view) {
        super.bind(view);

        unsubscribeOnUnbind(
                view.findByIpSelections()
                        .toFlowable(BackpressureStrategy.DROP)
                        .debounce(
                                FIND_DEBOUNCE_INTERVAL_SECONDS,
                                TimeUnit.SECONDS,
                                computationScheduler
                        )
                        .flatMap(
                                ø -> ipInfoService.findByIp(view.ip()).subscribeOn(ioScheduler)
                        )
                        .observeOn(mainScheduler)
                        .doOnError(ø -> view.showError())
                        .retryWhen(throwables -> throwables)
                        .subscribe(it -> view.showLocation(it.location()))
        );
    }
}
