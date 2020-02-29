package com.example.androiddevelopercodechallenge.view.activities.mainactivity

import android.content.Context
import com.example.androiddevelopercodechallenge.database.local.PictureDatabaseHelper
import com.example.androiddevelopercodechallenge.database.remote.PictureService
import com.example.androiddevelopercodechallenge.model.pictureresponse.PictureResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityPresenter : MainActivityContract.Presenter
{
    lateinit var viewContract : MainActivityContract.View
    lateinit var databaseHelper : PictureDatabaseHelper

    override fun initDatabase(context: Context)
    {
        databaseHelper = PictureDatabaseHelper(context)
    }

    override fun getDatabase(): PictureDatabaseHelper
    {
        return databaseHelper
    }


    override fun attatchView(view: MainActivityContract.View)
    {
        viewContract = view
    }

    override fun requestNewList()
    {
        val service = PictureService.getPictureCallService()
        var response = ArrayList<PictureResult>()
        CoroutineScope(Dispatchers.IO).launch {
            val pictureRequest = service.getPictureList()
            withContext(Dispatchers.Main) {
                response = pictureRequest.await()
                databaseHelper.insertPictureListToDatabase(response)
                viewContract.addPictureListToRecyclerView(response)
            }
        }
    }

    override fun requestNewListFromDatabase()
    {
        viewContract.addPictureListToRecyclerView(databaseHelper.getAllPicturesFromDatabase())
    }
}