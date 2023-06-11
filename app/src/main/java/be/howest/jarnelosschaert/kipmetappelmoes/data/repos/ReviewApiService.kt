package be.howest.jarnelosschaert.kipmetappelmoes.data.repos

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("reviews")
    suspend fun getReviews(): List<ReviewRetro>
}

data class ReviewRetro(
    val Id: Int,
    val UserId: Int,
    val Content: String,
    val Rating: Int,
    val Date: String
)

object ApiServiceClient {
    private const val BASE_URL = "https://mountainz.fly.dev/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
