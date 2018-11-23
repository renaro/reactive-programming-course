package com.renarosantos.reactivecoursesampleapp

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun creatingObservable() {
        Observable.just(1,2,3,4,5,6)
                .doOnSubscribe{
                    println("Subscribed")
                }
                .doOnNext{
                    println("Value = $it")
                }
                .doOnComplete { println("Completed") }
                .doOnError{ println("Error")}
                .subscribe()

        println("Test Finished")
    }

    @Test
    fun createSingle(){
        Single.just("Single")
                .doOnSuccess {  println(it)}
                .subscribe()
    }

    @Test
    fun createCompletable(){
        Completable.complete()
                .doOnComplete{ print("Completed")}
                .subscribe()
    }
}
