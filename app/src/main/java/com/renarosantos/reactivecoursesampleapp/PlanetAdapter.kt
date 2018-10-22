package com.renarosantos.reactivecoursesampleapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.people_card.view.*

class PlanetAdapter(private val planets: List<Planet>) : RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.people_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return planets.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        planets[position].let {
            viewHolder.bind(it)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(planet: Planet) {
            itemView.name.text = planet.name
            val url = "planets/${planet.id}.jpg"
            Glide.with(itemView.context).load(BuildConfig.IMAGE_URL.format(url)).into(itemView.image)
        }
    }
}
