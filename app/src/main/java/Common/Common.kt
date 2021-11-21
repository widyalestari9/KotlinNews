package Common

import Interface.NewService
import Remote.RetrofitClient
import java.lang.StringBuilder

object Common {
    val BASE_URL = "https://newsapi.org/"
    val API_KEY="d5096d9ed72b41c5b49d1e6518d4dcac"

    val newService:NewService
    get()=RetrofitClient.getClient(BASE_URL).create(NewsService::class.java)

    val newService:NewService
        get()=RetrofitClient.getClient(BASE_URL).create(NewService::class.java)

    fun getNewsAPI(source:String):String{
        val apiUrl = StringBuilder(" https://newsapi.org/v2/top-headlines?sources=")
            .append(source)
            .append("&apiKey")
            .append(API_KEY)
        return apiUrl
    }



}