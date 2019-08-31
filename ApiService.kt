package com.sampletopicapp.apis

import com.example.crudappkotlin.model.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

//    @retrofit2.http.GET("search/users")
//    fun search(@retrofit2.http.Query("q") query: String,
//               @retrofit2.http.Query("page") page: Int = 1,
//               @retrofit2.http.Query("per_page") perPage: Int = 20): io.reactivex.Observable<Result>

//    @POST("/api/user/login")
//    fun login(@Body user: User): io.reactivex.Observable<LoginResponse>

//    @GET("api/users?page=2")
//    fun fetchAllTopics(@HeaderMap headers: Map<String, String> ): io.reactivex.Observable<UserResponse>

    @GET("api/users?page=2")
    fun fetchAllTopics(): Single<Response<UserResponse>>

    /**
     * Companion object for the factory
     */
    companion object Factory {
        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .baseUrl("https://reqres.in")
                    .build()

            return retrofit.create(ApiService::class.java);
        }
    }
}