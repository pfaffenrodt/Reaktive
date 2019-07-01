package com.badoo.reaktive.test.observable

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.test.utils.assertEquals
import com.badoo.reaktive.test.utils.assertFalse
import com.badoo.reaktive.test.utils.assertTrue

fun <T> TestObservableObserver<T>.assertValues(expectedValues: List<T>) {
    assertEquals(expectedValues, values, "Observable values do not match")
}

fun <T> TestObservableObserver<T>.assertValues(vararg expectedValues: T) {
    assertValues(listOf(*expectedValues))
}

fun <T> TestObservableObserver<T>.assertValue(expectedValue: T) {
    assertValues(listOf(expectedValue))
}

fun TestObservableObserver<*>.assertNoValues() {
    assertValues(emptyList())
}

fun TestObservableObserver<*>.assertComplete() {
    assertTrue(isComplete, "Observable was not complete")
}

fun TestObservableObserver<*>.assertNotComplete() {
    assertFalse(isComplete, "Observable is complete")
}

fun <T> Observable<T>.test(): TestObservableObserver<T> =
    TestObservableObserver<T>()
        .also(::subscribe)