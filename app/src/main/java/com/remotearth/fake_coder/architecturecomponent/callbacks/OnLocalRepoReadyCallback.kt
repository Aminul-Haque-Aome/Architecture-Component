package com.remotearth.fake_coder.architecturecomponent.callbacks

import com.remotearth.fake_coder.architecturecomponent.models.Repository

interface OnLocalRepoReadyCallback {
    fun onLocalDataReady(data: ArrayList<Repository>)
}