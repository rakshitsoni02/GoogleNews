package com.rax.googlenews.core.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event
 * specially to observe event only once.
 * Instance of this event shouldn't be  multiple
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        // Observe the internal MutableLiveData
        super.observe(owner, Observer<T> { data ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(data)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        setValue(null)
    }

}