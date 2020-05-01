package com.badoo.reaktive.single

import com.badoo.reaktive.annotations.UseReturnValue
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.single.subscribe as subscribeRx

/**
 * Wrappers are normally exposed to Swift.
 * You might want to enable Objective-C generics,
 * please refer to the [documentation][https://kotlinlang.org/docs/reference/native/objc_interop.html#to-use]
 * for more information.
 * You can also extend the wrapper class if you need to expose any additional operators.
 */
open class SingleWrapper<out T : Any>(inner: Single<T>) : Single<T> by inner {

    @UseReturnValue
    fun subscribe(
        isThreadLocal: Boolean = false,
        onSubscribe: ((Disposable) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ): Disposable =
        subscribeRx(
            isThreadLocal = isThreadLocal,
            onSubscribe = onSubscribe,
            onError = onError,
            onSuccess = onSuccess
        )

    @UseReturnValue
    fun subscribe(
        isThreadLocal: Boolean = false,
        onSuccess: (T) -> Unit
    ): Disposable =
        subscribeRx(
            isThreadLocal = isThreadLocal,
            onSuccess = onSuccess
        )

    @UseReturnValue
    fun subscribe(
        isThreadLocal: Boolean = false,
        onError: (Throwable) -> Unit,
        onSuccess: (T) -> Unit
    ): Disposable =
        subscribeRx(
            isThreadLocal = isThreadLocal,
            onError = onError,
            onSuccess = onSuccess
        )
}

fun <T : Any> Single<T>.wrap(): SingleWrapper<T> = SingleWrapper(this)
