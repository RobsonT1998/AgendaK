package br.com.alexf.agendak.rx

import br.com.alexf.agendak.model.Aluno
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by alex on 23/07/17.
 */
class RxSchedulers {

    fun singleMainThread(execution: () -> Unit): Disposable? {
        return Single.fromCallable {
            execution()
        }.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun flowable(flowable: Flowable<List<Aluno>>, execution: (alunos: List<Aluno>) -> Unit): Disposable? {
        return flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { alunos ->
                    execution(alunos)
                }
    }

}