package com.widya.kotlinnews

import Adapter.ViewHolder.ListNewsAdapter
import Common.Common.getNewsAPI
import Interface.NewService
import Model.News
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Downloader
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import javax.security.auth.callback.Callback

class ListNews : AppCompatActivity() {

    var source=""
    var webHotUrl:String?=""

    lateinit var dialog: AlertDialog
    lateinit var mService:NewService
    lateinit var adapter: ListNewsAdapter
    lateinit var LayoutManager:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)

//        Init View
        mService = Common.newsService

        dialog = SpotsDialog(this)
         swipe_to_refresh.setOnRefreshListener { loadNews(source,true)}
        dialogLayout.setOnClickListener{
//            implement soon
        }
        list_news.setHasFixedSize(true)
        list_news.layoutManager = LinearLayoutManager


        if(intent != null)
        {
            source = intent.getStringExtra("source")
            if(!source.isEmpty())
                loadNews(source,false)
        }


    }

    private fun loadNews(source: String?, isRefreshec b: Boolean) {
        if(isRestricted)
        {
            dialog.show()
            mService.getNewsFromSource(Common.getNewsAPI(source!!))
                .enqueue(object : Callback<News> {
                    override fun onFailure(call: Call<News>?),t:Throwable?) {

                    }
                    override fun onResponse(call: Call<News>?, response: Downloader.Response<News>?){
                        dialog.dismiss()

                        Picasso.with(baseContext)
                            .load(response.body()!!.articles!![0].urlToImage)
                            .into(top_image)

                    }

                })
        }

    }
}