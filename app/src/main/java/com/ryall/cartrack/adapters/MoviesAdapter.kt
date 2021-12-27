package com.ryall.cartrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryall.cartrack.R
import com.ryall.cartrack.models.Search

class CustomAdapter( private val listener: OnItemClickListener
) : RecyclerView.Adapter<CustomAdapter.MovieViewHolder>() {

    private var listData : List<Search>? = null
    fun setListData(listData: List<Search>?){
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = listData?.get(position)
        holder.itemView.apply {
            if (movieItem?.Poster.equals("N/A")){
                holder.imageView.setImageResource(R.mipmap.no_image)
            }else{
                Glide.with(this).load(movieItem?.Poster).into(holder.imageView)
            }
            holder.textView.text = movieItem?.Title
        }
    }

    override fun getItemCount(): Int {
        if(listData == null )return 0
        return listData?.size!!
    }

    inner class MovieViewHolder(view: View ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}