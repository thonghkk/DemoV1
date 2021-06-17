package com.example.coroutines_example.get_video_on_drive

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coroutines_example.R
import com.example.coroutines_example.get_video_on_drive.video.PlayVideoActivity
import com.example.coroutines_example.get_video_on_drive.video.Video

class VideoAdapter(private val videos:List<Video>):RecyclerView.Adapter<VideoAdapter.VideoHolder>() {
    class VideoHolder(view: View):RecyclerView.ViewHolder(view) {

        val imgVideo = view.findViewById<ImageView>(R.id.imgVideo)
        val mLayoutItem = view.findViewById<RelativeLayout>(R.id.mLayoutItem)
        fun bindVideoItem(video:Video){
            Glide.with(itemView.context).load(video.path).into(imgVideo)
            mLayoutItem.setOnClickListener {
                val intent = Intent(itemView.context,PlayVideoActivity::class.java)
                intent.putExtra("thumb",video.thumb)
                intent.putExtra("path",video.path)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_video,parent,false)
        return VideoHolder(v)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bindVideoItem(videos[position])
     }

    override fun getItemCount() = videos.size
}