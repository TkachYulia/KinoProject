package com.example.kinoproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter(
    var list: List<Film>? = null,
    val itemClickListener: RecyclerViewItemClick? = null
) : RecyclerView.Adapter<FilmAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_film, p0, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
        p0.bind(list?.get(p1))
    }

    fun clearAll() {
        (list as? ArrayList<Film>)?.clear()
        notifyDataSetChanged()
    }

    inner class FilmViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(post: Film?) {

            val tvFilmId = view.findViewById<TextView>(R.id.tvFilmId)



            tvFilmId.text = post?.filmId.toString()


            view.setOnClickListener {
                itemClickListener?.itemClick(adapterPosition, post!!)
            }
        }
    }

    interface RecyclerViewItemClick {

        fun itemClick(position: Int, item: Film)
    }
}