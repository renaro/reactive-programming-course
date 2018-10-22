package com.renarosantos.reactivecoursesampleapp

import io.reactivex.Observable

interface HomeView {
    fun userInteraction(): Observable<HomeInteraction>
}

sealed class HomeInteraction {
    class NameTapped(val people: Person) : HomeInteraction()
    class InitialTapped(val people: Person) : HomeInteraction()
    object SeeAllPeopleTapped : HomeInteraction()
}
