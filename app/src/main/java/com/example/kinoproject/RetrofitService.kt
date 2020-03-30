package team.codebuster.retrofitexample

import com.example.kinoproject.Film
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitService {

    const val BASE_URL = "http://api.kinopoisk.cf/"

    fun getFilmApi(): PostApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
        return retrofit.create(PostApi::class.java)
    }
}

interface PostApi {

    @GET("getFilm")
    fun getFilmList(): Call<List<Film>>
    @GET("getGenres")
    fun getGenresist(): Call<List<Film>>
    @GET("getStaff")
    fun getStaffList(): Call<List<Film>>

}