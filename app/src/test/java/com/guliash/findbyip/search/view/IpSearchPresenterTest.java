package com.guliash.findbyip.search.view;

import com.guliash.findbyip.Stub;
import com.guliash.findbyip.core.utils.Irrelevant;
import com.guliash.findbyip.search.ip.IpInfoService;

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
    IpInfoService ipInfoService;

    @Mock
    IpSearchView view;

    private final TestScheduler scheduler = new TestScheduler();

    private PublishSubject<Object> findByIpSelections = PublishSubject.create();

    private IpSearchPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(ipInfoService.findByIp(anyString())).thenReturn(Flowable.just(Stub.IP_INFO));

        when(view.ip()).thenReturn("8.8.8.8");
        when(view.findByIpSelections()).thenReturn(findByIpSelections);

        presenter = new IpSearchPresenter(ipInfoService, scheduler, scheduler, scheduler);
    }

    @Test
    public void bindUnbindWithoutErrors() {
        presenter.bind(view);

        scheduler.advanceTimeBy(3, TimeUnit.SECONDS);

        presenter.unbind(view);
    }

    @Test
    public void showsLocationIfIpInfoServiceEmitsValue() {
        presenter.bind(view);

        findByIpSelections.onNext(Irrelevant.INSTANCE);
        scheduler.advanceTimeBy(3, TimeUnit.SECONDS);

        presenter.unbind(view);

        verify(view).showLocation(Stub.IP_INFO.location());
    }

    @Test
    public void showsErrorIfIpInfoServiceErrored() {
        when(ipInfoService.findByIp(anyString())).thenReturn(Flowable.error(new Throwable()));

        presenter.bind(view);

        findByIpSelections.onNext(Irrelevant.INSTANCE);
        scheduler.advanceTimeBy(3, TimeUnit.SECONDS);

        presenter.unbind(view);

        verify(view).showError();
    }

    @Test
    public void ifIpInfoServiceErroredOnceWillNotUnsubscribeFromIpInfoSelections() {
        when(ipInfoService.findByIp(anyString())).thenReturn(Flowable.error(new Throwable()));

        presenter.bind(view);

        findByIpSelections.onNext(Irrelevant.INSTANCE);
        scheduler.advanceTimeBy(3, TimeUnit.SECONDS);

        findByIpSelections.onNext(Irrelevant.INSTANCE);
        scheduler.advanceTimeBy(3, TimeUnit.SECONDS);

        presenter.unbind(view);

        verify(view, times(2)).showError();
    }

}
