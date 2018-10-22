package com.renarosantos.reactivecoursesampleapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HomeView {

    val cardsInteraction = PublishSubject.create<PeopleAdapter.Interaction>()
    val presenter = HomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list_people.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        list_planets.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        peopleAdapter()
        planetAdapter()
    }

    private fun peopleAdapter() {
        val peopleAdapter = PeopleAdapter(listOf(
                Person("1", "Luke Skywalker", 172, 77f, "blond", "1"),
                Person("2", "C-3PO", 167, 75f, "n/a", "1"),
                Person("3", "R2-D2", 167, 75f, "n/a", "1"),
                Person("4", "Darth Vader", 167, 75f, "n/a", "1")
        ), cardsInteraction)
        list_people.adapter = peopleAdapter
    }

    private fun planetAdapter() {
        val planetAdapter = PlanetAdapter(listOf(
                Planet("2", "Alderaan", 400f),
                Planet("3", "Yavin IV", 500f),
                Planet("4", "Hoth", 600f),
                Planet("5", "Dagobah", 700f)
        ))
        list_planets.adapter = planetAdapter
    }



    override fun userInteraction(): Observable<HomeInteraction> {
        val names = cardsInteraction.ofType(PeopleAdapter.Interaction.CardNameTapped::class.java)
                .map {
                    HomeInteraction.NameTapped(it.people)
                }
        val initials = cardsInteraction.ofType(PeopleAdapter.Interaction.CardInitialTapped::class.java)
                .map {
                    HomeInteraction.InitialTapped(it.people)
                }
        return Observable.merge(names, initials)
    }
}