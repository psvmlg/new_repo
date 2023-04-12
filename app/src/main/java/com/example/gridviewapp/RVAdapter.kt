package com.example.gridviewapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gridviewapp.model.GridImage

class RVAdapter(private val context: Context) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private var imageList = listOf<GridImage>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.grid_cell, parent, false))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(imageList[position].downloadUrl).fitCenter().centerCrop()
            .into(holder.imageView)
    }

    fun setImageList(newImageList: List<GridImage>) {
        imageList = newImageList
        notifyDataSetChanged()
    }
}