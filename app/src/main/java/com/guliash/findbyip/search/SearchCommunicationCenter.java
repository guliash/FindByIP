package com.guliash.findbyip.search;

import com.guliash.findbyip.core.utils.Irrelevant;
import com.guliash.findbyip.search.di.SearchScope;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

@SearchScope
public class SearchCommunicationCenter {

    private PublishSubject<Object> showOnMapSelections = PublishSubject.create();

    @Inject
    public SearchCommunicationCenter() {

    }

    public void showOnMapSelected() {
        showOnMapSelections.onNext(Irrelevant.INSTANCE);
    }

    public Observable<Object> showOnMapSelections() {
        return showOnMapSelections;
    }
}
