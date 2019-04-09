package com.remotearth.fake_coder.architecturecomponent.callbacks

import com.remotearth.fake_coder.architecturecomponent.models.Repository

interface OnRemoteRepoReadyCallback {
    fun onRemoteDataReady(data: ArrayList<Repository>)
}