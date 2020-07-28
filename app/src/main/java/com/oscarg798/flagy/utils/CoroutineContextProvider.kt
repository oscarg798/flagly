package com.oscarg798.flagly.utils

import kotlinx.coroutines.CoroutineDispatcher

class CoroutineContextProvider(
    val mainDispatcher: CoroutineDispatcher,
    val backgroundDispatcher: CoroutineDispatcher
)