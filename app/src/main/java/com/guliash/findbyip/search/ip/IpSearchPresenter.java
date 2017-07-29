package com.guliash.findbyip.search.ip;

import android.support.annotation.NonNull;

import com.guliash.findbyip.core.mvp.BasePresenter;
import com.guliash.findbyip.core.rx.ComputationScheduler;
import com.guliash.findbyip.core.rx.IoScheduler;
import com.guliash.findbyip.core.rx.MainScheduler;
import com.guliash.findbyip.search.SearchCommunicationCenter;
import com.guliash.findbyip.search.SearchState;
import com.guliash.findbyip.search.ip.service.IpInfoService;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Scheduler;

public class IpSearchPresenter extends BasePresenter<IpSearchView> {

    private final static int FIND_DEBOUNCE_INTERVAL_SECONDS = 1;

    private final SearchState searchState;
    private final IpInfoService ipInfoService;
    private final SearchCommunicationCenter searchCommunicationCenter;
    private final Scheduler mainScheduler;
    private final Scheduler ioScheduler;
    private final Scheduler computationScheduler;

    @Inject
    public IpSearchPresenter(@NonNull SearchState searchState,
                             @NonNull IpInfoService ipInfoService,
                             @NonNull SearchCommunicationCenter searchCommunicationCenter,
                             @NonNull @MainScheduler Scheduler mainScheduler,
                             @NonNull @IoScheduler Scheduler ioScheduler,
                             @NonNull @ComputationScheduler Scheduler computationScheduler) {
        this.searchState = searchState;
        this.ipInfoService = ipInfoService;
        this.searchCommunicationCenter = searchCommunicationCenter;
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
                        .doOnNext(searchState::setIpInfo)
                        .subscribe(it -> view.showLocation(it.location())),

                view.showOnMapSelections()
                        .subscribe(ø -> searchCommunicationCenter.showOnMapSelected())
        );
    }
}
