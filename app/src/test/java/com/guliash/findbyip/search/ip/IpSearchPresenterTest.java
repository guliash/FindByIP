package com.guliash.findbyip.search.ip;

import com.guliash.findbyip.Stub;
import com.guliash.findbyip.core.utils.Irrelevant;
import com.guliash.findbyip.search.SearchCommunicationCenter;
import com.guliash.findbyip.search.SearchState;
import com.guliash.findbyip.search.ip.model.IpInfo;
import com.guliash.findbyip.search.ip.service.IpInfoService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subjects.PublishSubject;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IpSearchPresenterTest {

    @Mock
    SearchState searchState;

    @Mock
    SearchCommunicationCenter searchCommunicationCenter;

    @Mock
    IpInfoService ipInfoService;

    @Mock
    IpSearchView view;

    private final TestScheduler scheduler = new TestScheduler();

    private PublishSubject<Object> findByIpSelections = PublishSubject.create();
    private PublishSubject<Object> showOnMapSelections = PublishSubject.create();

    private PublishSubject<IpInfo> searchStateIpInfo = PublishSubject.create();

    private IpSearchPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(ipInfoService.findByIp(anyString())).thenReturn(Flowable.just(Stub.IP_INFO));

        when(view.ip()).thenReturn("8.8.8.8");
        when(view.findByIpSelections()).thenReturn(findByIpSelections);
        when(view.showOnMapSelections()).thenReturn(showOnMapSelections);

        when(searchState.ipInfo()).thenReturn(searchStateIpInfo);

        presenter = new IpSearchPresenter(
                searchState,
                searchCommunicationCenter,
                ipInfoService,
                scheduler,
                scheduler,
                scheduler
        );
    }

    @Test
    public void bindUnbind_withoutErrors() {
        presenter.bind(view);

        scheduler.advanceTimeBy(3, TimeUnit.SECONDS);

        presenter.unbind(view);
    }

    @Test
    public void ipInfoServiceEmitsValue_updatesIpInfoState() {
        presenter.bind(view);

        findByIpSelections.onNext(Irrelevant.INSTANCE);
        scheduler.advanceTimeBy(3, TimeUnit.SECONDS);

        presenter.unbind(view);

        verify(searchState).setIpInfo(Stub.IP_INFO);
    }

    @Test
    public void ipInfoServiceEmitsError_showsError() {
        when(ipInfoService.findByIp(anyString())).thenReturn(Flowable.error(new Throwable()));

        presenter.bind(view);

        findByIpSelections.onNext(Irrelevant.INSTANCE);
        scheduler.advanceTimeBy(3, TimeUnit.SECONDS);

        presenter.unbind(view);

        verify(view).showError();
    }

    @Test
    public void ifIpInfoServiceEmitsError_willNotUnsubscribeFromIpInfoSelections() {
        when(ipInfoService.findByIp(anyString())).thenReturn(Flowable.error(new Throwable()));

        presenter.bind(view);

        findByIpSelections.onNext(Irrelevant.INSTANCE);
        scheduler.advanceTimeBy(3, TimeUnit.SECONDS);

        findByIpSelections.onNext(Irrelevant.INSTANCE);
        scheduler.advanceTimeBy(3, TimeUnit.SECONDS);

        presenter.unbind(view);

        verify(view, times(2)).showError();
    }

    @Test
    public void searchStateEmitsIpInfo_showsIpLocation() {
        presenter.bind(view);

        searchStateIpInfo.onNext(Stub.IP_INFO);

        presenter.unbind(view);

        verify(view).showLocation(Stub.IP_INFO.location());
    }


}
