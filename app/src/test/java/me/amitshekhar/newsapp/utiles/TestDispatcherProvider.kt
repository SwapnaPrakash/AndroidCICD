package me.amitshekhar.newsapp.utiles

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import me.amitshekhar.newsapp.utils.DispatcherProvider

class TestDispatcherProvider : DispatcherProvider {
    private val testDispatcherProvider = UnconfinedTestDispatcher()

    override val io: CoroutineDispatcher
        get() = testDispatcherProvider
    override val main: CoroutineDispatcher
        get() = testDispatcherProvider
    override val default: CoroutineDispatcher
        get() = testDispatcherProvider
}