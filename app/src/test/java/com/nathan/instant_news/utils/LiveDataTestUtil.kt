package com.nathan.instant_news.utils

import androidx.lifecycle.LiveData
import kotlinx.coroutines.delay

suspend fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2
): T {
    var counter = 0

    while(this.value == null) {
        if(counter > time) break
        counter++
        delay(100)
    }

    val data: T? = this.value

    @Suppress("UNCHECKED_CAST")
    return data as T
}