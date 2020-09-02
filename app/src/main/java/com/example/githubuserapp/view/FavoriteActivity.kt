package com.example.githubuserapp.view

import android.database.ContentObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.CONTENT_URI
import com.example.githubuserapp.db.FavoriteHelper
import com.example.githubuserapp.helper.MappingHelper
import com.example.githubuserapp.model.GithubUserFavorite
import com.example.githubuserapp.viewModel.FavoriteAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {
    private lateinit var adapter: FavoriteAdapter
    private lateinit var favoriteHelper: FavoriteHelper
    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.title = "Favorite User"

        favoriteHelper = FavoriteHelper.getInstance(applicationContext)
        favoriteHelper.open()

        rvFavorite.layoutManager = LinearLayoutManager(this)
        rvFavorite.setHasFixedSize(true)
        adapter = FavoriteAdapter()
        rvFavorite.adapter = adapter

        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        val myObserver = object : ContentObserver(handler) {
            override fun onChange(self: Boolean) {
                loadListFavorite()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)

        if (savedInstanceState == null) {
            loadListFavorite()
        } else {
            val list = savedInstanceState.getParcelableArrayList<GithubUserFavorite>(EXTRA_STATE)
            if (list != null) {
                adapter.listFavorites = list
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)
    }
    private fun loadListFavorite() {
        GlobalScope.launch(Dispatchers.Main) {
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = contentResolver?.query(CONTENT_URI, null, null, null, null)
                MappingHelper.mapCursorToArrayList(cursor)
            }
            val notes = deferredNotes.await()
            if (notes.size > 0) {
                adapter.listFavorites = notes
            } else {
                adapter.listFavorites = ArrayList()
                showSnackbarMessage("Tidak ada data saat ini")
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listFavorites)
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(rvFavorite, message, Snackbar.LENGTH_SHORT).show()
    }
    override fun onResume() {
        super.onResume()
        loadListFavorite()
    }
}