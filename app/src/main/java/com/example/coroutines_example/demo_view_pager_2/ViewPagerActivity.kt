package com.example.coroutines_example.demo_view_pager_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.coroutines_example.R
import com.example.coroutines_example.demo_view_pager_2.transformer.DepthPageTransformer
import com.example.coroutines_example.demo_view_pager_2.transformer.ZoomOutPageTransformer

/*
* Demo ViewPager 2
* Use library here : https://developer.android.com/jetpack/androidx/releases/viewpager2
* How to handling page transformer : https://developer.android.com/training/animation/screen-slide-2
*
* */

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var rdg1: RadioGroup
    private lateinit var rdg2: RadioGroup
    private lateinit var rdg3: RadioGroup
    private lateinit var viewPager2Adapter: ViewPager2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        initView()
        handlingEventButton()
    }

    private fun initView() {
        viewPager2 = findViewById(R.id.viewPager2)
        rdg1 = findViewById(R.id.rdg_1)
        rdg2 = findViewById(R.id.rdg_2)
        rdg3 = findViewById(R.id.rdg_3)

        //assign value default to RadioButton
        rdg1.check(R.id.rdb_leftToRight)
        rdg2.check(R.id.rdb_vertical)
        rdg3.check(R.id.rdb_normal)

        viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2.adapter = viewPager2Adapter

    }

    private fun handlingEventButton() {
        //set value for RadioGroup 1
        rdg1.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                //checkedId : Check your Id Current
                when (checkedId) {
                    R.id.rdb_leftToRight -> {
                        viewPager2.layoutDirection = View.LAYOUT_DIRECTION_LTR
                    }
                    R.id.rdb_rightToLeft -> {
                        viewPager2.layoutDirection = View.LAYOUT_DIRECTION_RTL
                    }
                }
            }
        })
        //set value for RadioGroup 2
        rdg2.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                //checkedId : Check your Id Current
                when (checkedId) {
                    R.id.rdb_vertical -> {
                        viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
                    }
                    R.id.rdb_horizontal -> {
                        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    }
                }
            }
        })

        //set value for RadioGroup 3
        rdg3.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                //checkedId : Check your Id Current
                when (checkedId) {
                    R.id.rdb_normal -> {
                        viewPager2.setPageTransformer(null)
                    }
                    R.id.rdb_zoomOut -> {
                        viewPager2.setPageTransformer(ZoomOutPageTransformer())
                    }
                    R.id.rdb_depth -> {
                        viewPager2.setPageTransformer(DepthPageTransformer())
                    }
                }
            }
        })
    }
}