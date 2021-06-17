package com.example.coroutines_example.get_video_on_drive

import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_example.R
import com.example.coroutines_example.get_video_on_drive.video.Video
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import hb.xvideoplayer.MxVideoPlayerWidget
import java.io.File
import java.net.FileNameMap
import java.util.jar.Manifest

/* using library: https://github.com/ParkSangGwon/TedPermission*/

class GetAllVideo : AppCompatActivity() {

    private lateinit var mRecycleViewVideo: RecyclerView
    private lateinit var mVideoAdapter: VideoAdapter

    val listsVideo = mutableListOf<Video>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_all_video)

        mRecycleViewVideo = findViewById(R.id.mRecycleViewVideo)

        val gridLayoutManager = GridLayoutManager(this, 2)
        mRecycleViewVideo.layoutManager = gridLayoutManager
        check()
    }

    private fun check() {
        val permissionListener = (object : PermissionListener {
            @RequiresApi(Build.VERSION_CODES.Q)
            override fun onPermissionGranted() {
                getAllVideoFromDrive()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@GetAllVideo, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        })

        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setDeniedMessage("Can not connect your video because you do not allow")
            .setPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .check()

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun getAllVideoFromDrive() {
        val cursor: Cursor?
        val columnIndexData = 0
        val thumb = 0
        var thumbnail: String?
        var absolutePathOfImage: String?
        val uri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media._ID, MediaStore.Video.Thumbnails.DATA
        )
        val orderBy = MediaStore.Images.Media.DATE_TAKEN
        cursor =
            applicationContext.contentResolver.query(uri, projection, null, null, "$orderBy DESC")!!

        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(columnIndexData)
            thumbnail = cursor.getString(thumb)
            val file = File(thumbnail.toString()).name


            val video = Video()
            video.path = absolutePathOfImage
            video.thumb = file
            Log.d("thumbnail", "getAllVideoFromDrive: $absolutePathOfImage $thumbnail")

            listsVideo.add(video)
        }
        mRecycleViewVideo.adapter = VideoAdapter(listsVideo)
    }

}

