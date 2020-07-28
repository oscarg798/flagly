package com.oscarg798.flagly.remoteconfig

import kotlinx.coroutines.Deferred

interface RemoteConfig {

    fun activateAsync(): Deferred<Unit>

    fun getBoolean(key: String): Boolean
}