package com.remotearth.fake_coder.architecturecomponent.repositories

import android.content.Context
import com.remotearth.fake_coder.architecturecomponent.data.local.GitRepoLocalDataSource
import com.remotearth.fake_coder.architecturecomponent.data.remote.GitRepoRemoteDataSource
import com.remotearth.fake_coder.architecturecomponent.models.Repository
import com.remotearth.fake_coder.architecturecomponent.wrappers.NetManager
import kotlin.collections.ArrayList
import io.reactivex.Observable

class GitRepoRepository(context: Context) {

    private val localDataSource = GitRepoLocalDataSource()
    private val remoteDataSource = GitRepoRemoteDataSource()

    private val netManager = NetManager(context)

    fun getRepositories(): Observable<ArrayList<Repository>> {
        netManager.isConnectedToInternet?.let {
            if (it) {
                return remoteDataSource.getRepositories().flatMap {
                    return@flatMap localDataSource
                        .saveRepositories(it)
                        .toSingleDefault(it)
                        .toObservable()
                }
            }
        }

        return localDataSource.getRepositories()
    }

}