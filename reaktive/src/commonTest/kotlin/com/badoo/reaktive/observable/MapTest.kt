package com.badoo.reaktive.observable

import com.badoo.reaktive.test.observable.TestObservable
import com.badoo.reaktive.test.observable.isError
import com.badoo.reaktive.test.observable.test
import com.badoo.reaktive.test.observable.values
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MapTest : UpstreamDownstreamGenericTests by UpstreamDownstreamGenericTests<Unit>({ map {} }) {

    private val upstream = TestObservable<String?>()
    private val observer = upstream.map { it?.length }.test()

    @Test
    fun maps_non_null_value() {
        upstream.onNext("abc")

        assertEquals(listOf(3), observer.values)
    }

    @Test
    fun maps_null_value() {
        upstream.onNext(null)

        assertEquals(listOf(null), observer.values)
    }

    @Test
    fun produces_error_WHEN_mapper_throws_an_exception() {
        val error = Throwable()

        val observer = upstream.map { throw error }.test()
        upstream.onNext("abc")

        assertTrue(observer.isError(error))
    }
}