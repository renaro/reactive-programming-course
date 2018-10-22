package com.renarosantos.reactivecoursesampleapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.people_card.view.*

class PeopleAdapter(private val people: List<Person>, val publisherSubject: PublishSubject<Interaction>) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.people_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        people[position].let {
            viewHolder.bind(it, publisherSubject)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(person: Person, publisherSubject: PublishSubject<Interaction>) {
            itemView.name.text = person.name
            itemView.name.clicks().subscribe {
                println(person.name)
                publisherSubject.onNext(Interaction.CardNameTapped(person))
            }
            val url = "characters/${person.id}.jpg"
            Glide.with(itemView.context).load(BuildConfig.IMAGE_URL.format(url)).into(itemView.image)
        }
    }

    sealed class Interaction {
        data class CardNameTapped(val people: Person) : Interaction()
        data class CardInitialTapped(val people: Person) : Interaction()
    }

}
