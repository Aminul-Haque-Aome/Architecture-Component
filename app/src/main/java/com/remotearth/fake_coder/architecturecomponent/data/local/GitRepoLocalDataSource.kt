package com.remotearth.fake_coder.architecturecomponent.data.local

import android.os.Handler
import com.remotearth.fake_coder.architecturecomponent.callbacks.OnLocalRepoReadyCallback
import com.remotearth.fake_coder.architecturecomponent.models.Repository

class GitRepoLocalDataSource {

    fun getRepositories(onRepositoryReadyCallback: OnLocalRepoReadyCallback) {
        var repoList = ArrayList<Repository>()

        repoList.add(Repository("FireBase Auth","AomeDIU",100,false))
        repoList.add(Repository("BloodBond", "receme", 30, true))
        repoList.add(Repository("Expense Tracer", "Tahmid",430, false))
        repoList.add(Repository("Exertion", "Ashik", 30, false))
        repoList.add(Repository("Prank King", "Amin", 40, false))
        repoList.add(Repository("Self Protect","Saddam",4,true))
        repoList.add(Repository("NewsGist","Aslam Hossain",430,false))
        repoList.add(Repository("BSC Prep Key","Aslam Hossain",430,false))

        Handler().postDelayed({
            onRepositoryReadyCallback.onLocalDataReady(repoList)
        }, 2000)
    }

    fun saveRepositories(arrayList: ArrayList<Repository>){
        //todo save repositories in DB
    }
}