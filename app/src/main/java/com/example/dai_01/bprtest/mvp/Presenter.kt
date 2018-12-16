package com.example.dai_01.bprtest.mvp

import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.Observable

interface Presenter<in T: View> {

    fun onAttach(view: T)
    fun onDetach()

}