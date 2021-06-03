package com.example.coroutines_example.get_video_on_drive.video

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutines_example.R
import hb.xvideoplayer.MxVideoPlayer
import hb.xvideoplayer.MxVideoPlayerWidget

/*Using library https://github.com/henryblue/MxVideoPlayer*/
class PlayVideoActivity : AppCompatActivity() {
    private lateinit var mpw_video_player: MxVideoPlayerWidget

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)

        mpw_video_player = findViewById(R.id.mpw_video_player)

        val pathVideo = intent.getStringExtra("thumb")
        val pathVideo_ = intent.getStringExtra("path")
        Log.d("path", "onCreate: $pathVideo || $pathVideo_")

        try {
            mpw_video_player.startPlay(pathVideo, MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "Video Name")

        }catch (e:Exception){
            Log.d("exception", "onCreate: ${e.message}")
        }
    }

    override fun onPause() {
        super.onPause()
        MxVideoPlayer.releaseAllVideos()
    }

}