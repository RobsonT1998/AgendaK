package br.com.alexf.agendak.rx

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by alex on 23/07/17.
 */
class RxSchedulers {

    fun singleMainThread(execution: () -> Unit) {
        Single.fromCallable {
            execution()
        }.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
    
}