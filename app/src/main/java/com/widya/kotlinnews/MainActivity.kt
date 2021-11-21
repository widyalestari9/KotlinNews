package com.widya.kotlinnews

import Adapter.ViewHolder.ListSourceAdapter
import Interface.NewService
import Model.WebSite
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ThemedSpinnerAdapter
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var mServiece: NewService
    lateinit var adapter: ListSourceAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Init cache DE
        Paper.init(this)
//        Init sevice
        mServiece = Common.newsService

//        Init View
        swipe_to_refresh.setOnfreshListener{
            loadWebsiteSource(true)
        }
        recycler_view_source_news.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyler_view_source_news.layoutManager = layoutManager

        dialog = SpotsDialog(this)

        loadWebsiteSource(false)
    }

    private fun loadWebsiteSource(isRefresh: Boolean) {

        if(!isRefresh)
        {
            val cache = Paper.book().read<String>("cache")
            if(chace !=null && !cache.isBlank() && cache !="null")
            {
//              Read cache
                val webSite = Gson().fromJson<Website>(cache,Website::class.java)
                adapter = ListSourceAdapter(baseContext,website)
                adapter.notifyDataSetChanged()
                recycler_view_source_news.adapter = adapter
            }
            else
            {
//                load website and write cache
                dialog.show()
//                fetch new data
                mServiece.sources.enqueue(object : retrofit2.Callback<WebSite>{
                    //                    ctrl+o
                    override fun onFailure(call: Call<WebSite>, t: Throwable) {
                        Toast.makeText(baseContext,"Failed",Toast.LENGTH_SHORT).show()

                    }
                    override fun onResponse(call: Call<WebSite>, response: Response<WebSite>) {
                        adapter = ListSourceAdapter(baseContext,response!!.body()!!)
                        adapter.notifyDataSetChanged()
                        recycler_view_source_new.adapter = adapter

//                        Save to cache
                        paper.book().write("cache",Gson().toJson(response!!.body()!!))

                        dialog.dismiss()
                    }


                })
            }
        }
        else
        {
            swipe_to_refreshing=true
//            Fetch new data
            mServiece.sources.enqueue(object : retrofit2.Callback<WebSite>{
                //                    ctrl+o
                override fun onFailure(call: Call<WebSite>, t: Throwable) {
                    Toast.makeText(baseContext,"Failed",Toast.LENGTH_SHORT).show()

                }
                override fun onResponse(call: Call<WebSite>, response: Response<WebSite>) {
                    adapter = ListSourceAdapter(baseContext,response!!.body()!!)
                    adapter.notifyDataSetChanged()
                    recycler_view_source_new.adapter = adapter

//                        Save to cache
                    paper.book().write("cache",Gson().toJson(response!!.body()!!))

                    swipe_to_refresh.isRefreshing=false
                }

            })
        }
    }
}