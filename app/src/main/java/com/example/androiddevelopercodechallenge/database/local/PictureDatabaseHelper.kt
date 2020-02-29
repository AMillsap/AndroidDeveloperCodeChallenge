package com.example.androiddevelopercodechallenge.database.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.androiddevelopercodechallenge.model.pictureresponse.PictureResult

class PictureDatabaseHelper (context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    override fun onCreate(database: SQLiteDatabase?)
    {
        database?.execSQL(CREATE_PICTURE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun insertPictureListToDatabase(list : ArrayList<PictureResult>)
    {
        val database = writableDatabase
        val contentValues = ContentValues()

        for(i in 0 until list.size)
        {
            contentValues.put(COL_AUTHOR, list[i].author)
            contentValues.put(COL_PIC_URL, list[i].download_url)
            contentValues.put(COL_HEIGHT, list[i].height)
            contentValues.put(COL_ID, list[i].id)
            contentValues.put(COL_URL, list[i].url)
            contentValues.put(COL_WIDTH, list[i].width)
            database.insert(TABLE_NAME, null, contentValues)
        }
        database.close()
    }

    fun getAllPicturesFromDatabase() : ArrayList<PictureResult>
    {
        val database = readableDatabase
        var pictureList : ArrayList<PictureResult> = ArrayList<PictureResult>()
        val cursor = database
            .rawQuery("SELECT * FROM $TABLE_NAME",
                null)

        if(cursor.moveToFirst()) {
            do {
                val author = cursor.getString(cursor.getColumnIndex(COL_AUTHOR))
                val width = cursor.getInt(cursor.getColumnIndex(COL_WIDTH))
                val height = cursor.getInt(cursor.getColumnIndex(COL_HEIGHT))
                val pictureURL = cursor.getString(cursor.getColumnIndex(COL_PIC_URL))
                val id = cursor.getString(cursor.getColumnIndex(COL_ID))
                val url = cursor.getString(cursor.getColumnIndex(COL_URL))
                val picture = PictureResult(author, pictureURL, height, id, url, width)
                pictureList.add(picture)
            } while(cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return pictureList
    }
    fun isDatabaseEmpty() : Boolean
    {
        val database = readableDatabase
        var pictureList : ArrayList<PictureResult> = ArrayList<PictureResult>()
        val cursor = database
            .rawQuery("SELECT * FROM $TABLE_NAME",
                null)
        if(cursor.moveToFirst()) {
            do {
                val author = cursor.getString(cursor.getColumnIndex(COL_AUTHOR))
                if(author == "")
                {
                    cursor.close()
                    database.close()
                    return true
                }
                else
                {
                    cursor.close()
                    database.close()
                    return false
                }
            } while(cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return true
    }

}