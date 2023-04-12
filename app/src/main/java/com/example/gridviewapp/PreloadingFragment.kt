package com.example.gridviewapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gridviewapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class PreloadingFragment : Fragment() {
    lateinit var gridRecyclerView: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_preloading, container, false)
        gridRecyclerView = view.findViewById<RecyclerView?>(R.id.recycleView).apply {
            layoutManager = GridLayoutManager(context, 2)
            rvAdapter = RVAdapter(context)
            adapter = rvAdapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("VARDAAN2", System.currentTimeMillis().toString())
        fetchAndShowImage()
    }

    private fun fetchAndShowImage() {
        lifecycleScope.launch {
            val response = RetrofitInstance.api.getImageList()
            if (response.isSuccessful) {
                response.body()?.let {
                    rvAdapter.setImageList(it)
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PreloadingFragment()
    }
}