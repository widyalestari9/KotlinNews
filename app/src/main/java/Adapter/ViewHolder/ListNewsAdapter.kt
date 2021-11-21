package Adapter.ViewHolder

import Common.ISO8601Parser
import Interface.ItemClickListener
import Model.Article
import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.internal.bind.util.ISO8601Utils
import com.squareup.picasso.Picasso
import com.widya.kotlinnews.R
import java.text.ParseException

class ListNewsAdapter (val articleList:List<Article>,private val context: Context):RecyclerView.Adapter<ListNewViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewViewHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        val itemView = inflater.inflate(R.layout.news_layout,parent,false)
        return ListNewViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ListNewViewHolder, position: Int) {
        Picasso.with(context)
            .load(articleList[position].urlToImage)
            .into(holder.article_image)
        if (articleList[position].title!!.length > 65) {
            holder.article_title.text = articleList[position].title!!.substring(0, 65) + "..."
        } else {
            holder.article_title.text = articleList[position].title
        }
        if (articleList[position].publishedAt !=null)
        {
            var date: ContactsContract.Contacts.Data?=null
            try {
                date =ISO8601Parser.parse(articleList[position].publishedAt)
            }catch (ex:ParseException)
            {
                ex.printStrackTrace()
            }
            holder.article_time.setReferenceTime(date!!.time)
        }

//        SET Event Click
        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View, position: Int) {
//               wiil implement
            }

        })




    }
}
