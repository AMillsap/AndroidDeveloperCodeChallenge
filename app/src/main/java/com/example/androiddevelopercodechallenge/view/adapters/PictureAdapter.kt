package com.example.androiddevelopercodechallenge.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androiddevelopercodechallenge.R
import com.example.androiddevelopercodechallenge.model.pictureresponse.PictureResult
import kotlinx.android.synthetic.main.picture_item.view.*

class PictureAdapter : RecyclerView.Adapter<PictureAdapter.ViewHolder>()
{
    var pictureList  = ArrayList<PictureResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false))

    override fun getItemCount(): Int = pictureList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.populatePicture(pictureList[position])
    }

    fun addList(list : ArrayList<PictureResult>)
    {
        pictureList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun populatePicture(pictureResponse: PictureResult)
        {
            itemView.tvAuthor.text = "Author: " + pictureResponse.author
            itemView.tvHeight.text = "Height: " + pictureResponse.height.toString()
            itemView.tvWidth.text = "Width: " + pictureResponse.width.toString()

            if(pictureResponse.download_url != null)
            {
                val url = pictureResponse.download_url
                Glide
                    .with(itemView)
                    .load(url)
                    .into(itemView.ivImage)
            }
        }
    }
}