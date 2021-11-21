package Interface

import Model.News
import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Url
import Model.WebSite as WebSite

interface NewService {
//masukin kode apinya
    @get:GET("v2/source?apiKey=d5096d9ed72b41c5b49d1e6518d4dcac")
    val source: Call<WebSite>

    @GET
    fun getNewsFromSource(@Url url:String):Call<News>
}