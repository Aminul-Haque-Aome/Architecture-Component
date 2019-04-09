package com.remotearth.fake_coder.architecturecomponent.viewModels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.remotearth.fake_coder.architecturecomponent.callbacks.OnRepositoryReadyCallback
import com.remotearth.fake_coder.architecturecomponent.repositories.GitRepoRepository
import com.remotearth.fake_coder.architecturecomponent.models.Repository

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var gitRepoRepository: GitRepoRepository = GitRepoRepository(application)

    var isLoading = ObservableField<Boolean>()
    var repositories = MutableLiveData<ArrayList<Repository>>()

    fun loadRepositories() {
        isLoading.set(true)

        gitRepoRepository.getRepositories(object : OnRepositoryReadyCallback {
            override
            fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.postValue(data)
            }
        })
    }
}