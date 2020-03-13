package com.rax.googlenews.core.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rax.googlenews.core.view.activity.BaseActivity

/**
 * Synthetic sugaring to get instance of [ViewModel] for [AppCompatActivity].
 */
inline fun <reified T : ViewModel> BaseActivity.getViewModel(): T {
    return ViewModelProvider(this, viewModelFactory).get(T::class.java)
}

/**
 * ViewModel is scoped to activity
 */
inline fun <reified T : ViewModel> Fragment.getViewModel(): T {
    return ViewModelProvider(this, (activity as BaseActivity).viewModelFactory).get(T::class.java)
}
