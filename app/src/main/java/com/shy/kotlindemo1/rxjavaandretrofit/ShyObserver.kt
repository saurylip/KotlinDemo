package com.shy.kotlindemo1.rxjavaandretrofit

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * observer
 */

internal abstract class ShyObserver<M> : Observer<M> {

    abstract fun onSuccess(model: M)
    abstract fun onError(errorMsg : String?)

    override fun onSubscribe(d: Disposable) {}

    override fun onNext(model: M) {
        onSuccess(model)
    }

    override fun onError(e: Throwable) {
        onError(e.message)
    }

    override fun onComplete() {}
}
