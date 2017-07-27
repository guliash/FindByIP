package com.guliash.findbyip.core.mvp;

import android.support.annotation.CallSuper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V> {

    protected V view;
    private CompositeDisposable unbindDisposables = new CompositeDisposable();

    @CallSuper
    public void bind(V view) {
        if (this.view != null) {
            throw new IllegalStateException("Presenter already has view");
        }
        this.view = view;
    }

    public void unbind(V view) {
        if (view != this.view) {
            throw new IllegalStateException("Presenter has unbound view");
        }
        unbindDisposables.clear();
        this.view = null;
    }

    protected void unsubscribeOnUnbind(Disposable... disposables) {
        unbindDisposables.addAll(disposables);
    }
}
