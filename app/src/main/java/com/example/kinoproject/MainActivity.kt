package com.example.kinoproject

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Response
import team.codebuster.retrofitexample.RetrofitService
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
   // lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private var postAdapter: FilmAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

      /*  swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            postAdapter?.clearAll()
            getPosts()
        }*/

        postAdapter = FilmAdapter(itemClickListener = this)
        recyclerView.adapter = postAdapter

        getPosts()
    }

    override fun itemClick(position: Int, item: Film) {
        val intent = Intent(this, FilmDetailActivity::class.java)
        intent.putExtra("post_id", item.filmId)
        startActivity(intent)
    }

    private fun getPosts() {
       // swipeRefreshLayout.isRefreshing = true
        RetrofitService.getFilmApi().getFilmList().enqueue(object : Callback<List<Film>> {
            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
               // swipeRefreshLayout.isRefreshing = false
            }

            override fun onResponse(call: Call<List<Film>>, response: Response<List<Film>>) {
                Log.d("My_film_list", response.body().toString())
                if (response.isSuccessful) {
                    val list = response.body()
                    postAdapter?.list = list
                    postAdapter?.notifyDataSetChanged()
                }
                //swipeRefreshLayout.isRefreshing = false
            }
        })
    }
}
