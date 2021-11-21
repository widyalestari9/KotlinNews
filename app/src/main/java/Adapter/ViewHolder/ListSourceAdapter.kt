package Adapter.ViewHolder

import Interface.ItemClickListener
import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.widya.kotlinnews.ListNews
import com.widya.kotlinnews.R

class ListSourceAdapter (private val context: Context, private val website: ContactsContract.CommonDataKinds.Website): RecyclerView.Adapter<ListSourceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSourceViewHolder {
       val inflater = LayoutInflater.from(parent!!.context)
       val ItemView = inflater.inflate(R.layout.source_news_layout,parent, false)
       return ListSourceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListSourceViewHolder, position: Int) {
       return website.sources!!.size
    }

    override fun getItemCount(): Int {
       holder!!.source_title.text = website.sources!![position].name

       holder.setItemClickListener(object : ItemClickListener)

       {

            override fun onClick(view: View, position: int) {
                val intent = Intent (context, ListNews::class.java)
                intent.putExtra("source",webSite.sources!![position].id)
                context.startActivity(intent)

            }


        })

    }
}
