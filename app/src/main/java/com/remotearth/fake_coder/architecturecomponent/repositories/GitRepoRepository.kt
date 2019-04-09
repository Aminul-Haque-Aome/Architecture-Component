package com.remotearth.fake_coder.architecturecomponent.repositories

import android.content.Context
import com.remotearth.fake_coder.architecturecomponent.callbacks.OnLocalRepoReadyCallback
import com.remotearth.fake_coder.architecturecomponent.callbacks.OnRemoteRepoReadyCallback
import com.remotearth.fake_coder.architecturecomponent.callbacks.OnRepositoryReadyCallback
import com.remotearth.fake_coder.architecturecomponent.data.local.GitRepoLocalDataSource
import com.remotearth.fake_coder.architecturecomponent.data.remote.GitRepoRemoteDataSource
import com.remotearth.fake_coder.architecturecomponent.models.Repository
import com.remotearth.fake_coder.architecturecomponent.wrappers.NetManager

class GitRepoRepository(context: Context) {

    private val localDataSource = GitRepoLocalDataSource()
    private val remoteDataSource = GitRepoRemoteDataSource()

    private val netManager = NetManager(context)

    fun getRepositories(onRepositoryReadyCallback: OnRepositoryReadyCallback) {
        netManager.isConnectedToInternet?.let {
            if (it) {
                remoteDataSource.getRepositories(object : OnRemoteRepoReadyCallback {
                    override
                    fun onRemoteDataReady(data: ArrayList<Repository>) {
                        localDataSource.saveRepositories(data)
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            } else {
                localDataSource.getRepositories(object : OnLocalRepoReadyCallback {
                    override
                    fun onLocalDataReady(data: ArrayList<Repository>) {
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            }
        }

    }

}