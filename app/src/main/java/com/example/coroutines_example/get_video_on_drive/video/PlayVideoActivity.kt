package com.example.coroutines_example.get_video_on_drive.video

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.coroutines_example.R
import com.example.coroutines_example.get_video_on_drive.GetAllVideo
import hb.xvideoplayer.MxVideoPlayer
import hb.xvideoplayer.MxVideoPlayerWidget

/*Using library https://github.com/henryblue/MxVideoPlayer*/
class PlayVideoActivity : AppCompatActivity() {
    private lateinit var mpw_video_player: MxVideoPlayerWidget
    var nameVideo: String = ""
    var pathVideo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)

        mpw_video_player = findViewById(R.id.mpw_video_player)

        nameVideo = intent.getStringExtra("thumb").toString()
        pathVideo = intent.getStringExtra("path").toString()
        Log.d("path", "onCreate: $pathVideo || $nameVideo")

        try {
//            MxVideoPlayerWidget.startFullscreen(
//                this,
//                MxVideoPlayerWidget::class.java,
//                pathVideo,
//                nameVideo
//            )
           mpw_video_player.startPlay(pathVideo, MxVideoPlayer.SCREEN_LAYOUT_NORMAL, nameVideo)
//            mpw_video_player.autoStartPlay(
//                pathVideo,
//                MxVideoPlayer.SCREEN_LAYOUT_NORMAL,
//                nameVideo
//            )
        } catch (e: Exception) {
            Log.d("exception", "onCreate: ${e.message}")
        }
    }

    override fun onPause() {
        super.onPause()
        MxVideoPlayer.releaseAllVideos()
    }

    override fun onBackPressed() {
        if (MxVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("name", nameVideo)
        outState.putString("path", pathVideo)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        nameVideo = savedInstanceState.getString("name").toString()
        pathVideo = savedInstanceState.getString("path").toString()
    }
}