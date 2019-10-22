package com.dididi.tbsfiledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(activityMainToolbar)
        activityMainToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuToolbarSearch -> {
                    Toast.makeText(this, "click search!", Toast.LENGTH_SHORT).show()
                }
                R.id.menuToolbarSetting -> {
                    Toast.makeText(this, "click setting!", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    /**
     * 设置toolbar为自定义样式
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
}
