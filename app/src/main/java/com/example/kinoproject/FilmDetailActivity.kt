package com.example.kinoproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import team.codebuster.retrofitexample.RetrofitService

class FilmDetailActivity : AppCompatActivity() {

   // private lateinit var progressBar: ProgressBar
    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)

        //progressBar = findViewById(R.id.progressBar)
        tvTitle = findViewById(R.id.tvTitle)
        tvBody = findViewById(R.id.tvBody)

        val filmId = intent.getIntExtra("film_id", 1)
        getFilm(id = filmId)
    }

    private fun getFilm(id: Int) {
        RetrofitService.getFilmApi().getFilmById(id).enqueue(object : Callback<Film> {
            override fun onFailure(call: Call<Film>, t: Throwable) {
                //progressBar.visibility = View.GONE
            }

            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                //progressBar.visibility = View.GONE
                val film = response.body()
                if (film != null) {
                    tvBody.text = film.body
                    tvTitle.text = film.title
                }
            }
        })
    }
}