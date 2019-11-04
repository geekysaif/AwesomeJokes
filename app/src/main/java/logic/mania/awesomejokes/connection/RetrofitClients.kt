package logic.mania.awesomejokes.connection

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClients {

    var retrofit: Retrofit? = null

    fun getClients(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            val gson = GsonBuilder()
                .setLenient().create()

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().readTimeout(1200, TimeUnit.SECONDS)
                .writeTimeout(1200, TimeUnit.SECONDS).addInterceptor(interceptor).build()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }
}