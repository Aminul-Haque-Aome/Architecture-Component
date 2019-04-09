package com.remotearth.fake_coder.architecturecomponent.callbacks

import com.remotearth.fake_coder.architecturecomponent.models.Repository

interface OnRepositoryReadyCallback {
    fun onDataReady(data: ArrayList<Repository>)
}