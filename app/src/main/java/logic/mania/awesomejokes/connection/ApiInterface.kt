package logic.mania.awesomejokes.connection

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("GetCategories")
    abstract fun GetCategories()

    @FormUrlEncoded
    @POST("GetSubCategories")
    abstract fun GetSubCategories(@Field("id") id: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("GetData")
    abstract fun GetData(@Field("id") id: String ): Call<ResponseBody>
}