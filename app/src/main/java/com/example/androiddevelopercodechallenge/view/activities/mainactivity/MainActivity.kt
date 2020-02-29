package com.example.androiddevelopercodechallenge.view.activities.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddevelopercodechallenge.R
import com.example.androiddevelopercodechallenge.database.remote.PictureService
import com.example.androiddevelopercodechallenge.model.pictureresponse.PictureResult
import com.example.androiddevelopercodechallenge.view.adapters.PictureAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), MainActivityContract.View
{

    val presenterContract = MainActivityPresenter() as MainActivityContract.Presenter
    val adapter by lazy { PictureAdapter() }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenterContract.attatchView(this)
        rvPictureList.layoutManager = LinearLayoutManager(this)
        rvPictureList.adapter = adapter
        presenterContract.initDatabase(this)
        if(presenterContract.getDatabase().isDatabaseEmpty())
        {
            var toast = Toast.makeText(this, "Getting data from the internet", Toast.LENGTH_LONG)
            toast.show()
            presenterContract.requestNewList()
        }
        else
        {
            var toast = Toast.makeText(this, "Getting data from the database", Toast.LENGTH_LONG)
            toast.show()
            presenterContract.requestNewListFromDatabase()
        }
    }

    override fun addPictureListToRecyclerView(pictureResult: ArrayList<PictureResult>)
    {
        adapter.addList(pictureResult)
    }


}
