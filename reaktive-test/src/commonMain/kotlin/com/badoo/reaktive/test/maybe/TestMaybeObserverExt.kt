package com.badoo.reaktive.test.maybe

import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.test.utils.assertEquals
import com.badoo.reaktive.test.utils.assertFalse
import com.badoo.reaktive.test.utils.assertTrue

fun <T> TestMaybeObserver<T>.assertSuccess() {
    assertTrue(isSuccess, "Maybe did not success")
}

fun <T> TestMaybeObserver<T>.assertSuccess(expectedValue: T) {
    assertEquals(expectedValue, value, "Maybe success values do not match")
}

fun <T> TestMaybeObserver<T>.assertNotSuccess() {
    assertFalse(isSuccess, "Maybe is succeeded")
}

fun TestMaybeObserver<*>.assertComplete() {
    assertTrue(isComplete, "Maybe did not complete")
}

fun TestMaybeObserver<*>.assertNotComplete() {
    assertFalse(isComplete, "Maybe is complete")
}

fun <T> Maybe<T>.test(): TestMaybeObserver<T> =
    TestMaybeObserver<T>()
        .also(::subscribe)