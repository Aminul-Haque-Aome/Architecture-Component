package com.remotearth.fake_coder.architecturecomponent.viewModels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.remotearth.fake_coder.architecturecomponent.repositories.GitRepoRepository
import com.remotearth.fake_coder.architecturecomponent.models.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var gitRepoRepository: GitRepoRepository = GitRepoRepository(application)
    private val compositeDisposable = CompositeDisposable()

    var isLoading = ObservableField<Boolean>()
    var repositories = MutableLiveData<ArrayList<Repository>>()

    fun loadRepositories() {
        isLoading.set(true)

        compositeDisposable.add(
            gitRepoRepository.getRepositories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ArrayList<Repository>>() {
                    override
                    fun onError(e: Throwable) {
                        //todo show Error message
                    }

                    override
                    fun onNext(data: ArrayList<Repository>) {
                        repositories.value = data
                    }

                    override
                    fun onComplete() {
                        isLoading.set(false)
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()

        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}