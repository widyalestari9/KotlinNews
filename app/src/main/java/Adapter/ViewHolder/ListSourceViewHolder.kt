package Adapter.ViewHolder


class ListSourceViewHolder(itemView:View):RecyclerView.ViewHolder(itemView), View.OnClickListener{

    private lateinit var itemClickListener:ItemClickListener

    var source_title = itemView.source_news_name

    fun getItemClickListener(itemClickListener: ItemClickListener)
    {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener.onClick(v!!,adapterPosition)
    }

}