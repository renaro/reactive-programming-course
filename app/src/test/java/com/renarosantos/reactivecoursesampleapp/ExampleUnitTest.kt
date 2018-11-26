package com.renarosantos.reactivecoursesampleapp

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun creatingObservable() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .doOnSubscribe { println("Subscribed") }
                .doOnNext { println("Value = $it") }
                .doOnComplete { println("Completed") }
                .doOnError { println("Error") }
                .subscribe()

        println("Test Finished")
    }

    @Test
    fun createSingle() {
        Single.just("Single")
                .doOnSuccess { println(it) }
                .subscribe()
    }

    @Test
    fun createSingleError() {
        Single.error<Int>(IllegalArgumentException())
                .doOnSuccess { println(it) }
                .doOnError { println(it) }
                .subscribe()
    }

    @Test
    fun createCompletable() {
        Completable.complete()
                .doOnComplete { print("Completed") }
                .subscribe()
    }

    @Test
    fun createCompletableWithError() {
        Completable.error(IllegalArgumentException())
                .doOnComplete { print("Completed") }
                .subscribe()
    }

    @Test
    fun takeFirstTwo() {
        Observable.just(21, 22, 23, 24, 25, 26)
                .takeLast(2)
                .filter { it -> it > 24 }
                .doOnNext { println("$it") }
                .subscribe()
    }
}
