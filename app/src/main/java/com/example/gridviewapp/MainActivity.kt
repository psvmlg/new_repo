package com.example.gridviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gridviewapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment = PreloadingFragment.newInstance()
        button = findViewById<Button?>(R.id.button).apply {
            setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, PreloadingFragment.newInstance())
                    .commitAllowingStateLoss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        addFragment()
    }

    @Synchronized
    fun addFragment() {
        if (supportFragmentManager.fragments.none { it.tag == "MY_TAG" }) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout, fragment, "MY_TAG").hide(fragment)
                .commit()
        }
    }
}