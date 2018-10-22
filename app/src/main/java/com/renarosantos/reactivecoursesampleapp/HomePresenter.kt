package com.renarosantos.reactivecoursesampleapp

class HomePresenter(view: HomeView) {

    init {
        view.userInteraction().map {
            println(it)
        }.subscribe()
    }
}
