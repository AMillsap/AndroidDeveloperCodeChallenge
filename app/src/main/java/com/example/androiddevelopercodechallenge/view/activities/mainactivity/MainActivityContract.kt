package com.example.androiddevelopercodechallenge.view.activities.mainactivity

import android.content.Context
import com.example.androiddevelopercodechallenge.database.local.PictureDatabaseHelper
import com.example.androiddevelopercodechallenge.model.pictureresponse.PictureResult

interface MainActivityContract
{
    interface View
    {
        fun addPictureListToRecyclerView(pictureResult: ArrayList<PictureResult>)
    }

    interface Presenter
    {
        fun attatchView(view : View)
        fun requestNewList()
        fun requestNewListFromDatabase()
        fun initDatabase(context: Context)
        fun getDatabase() : PictureDatabaseHelper
    }
}