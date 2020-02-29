package com.example.androiddevelopercodechallenge.database.local

const val DATABASE_NAME = "data_per_database"
const val TABLE_NAME = "picture_table"
const val DATABASE_VERSION = 1
const val COL_AUTHOR = "author"
const val COL_PIC_URL = "picture_url"
const val COL_HEIGHT = "height"
const val COL_ID = "id"
const val COL_URL = "url"
const val COL_WIDTH = "width"

const val CREATE_PICTURE_TABLE =
    "CREATE TABLE $TABLE_NAME (" +
            "$COL_AUTHOR String," +
            "$COL_PIC_URL String PRIMARY_KEY," +
            "$COL_HEIGHT Int," +
            "$COL_ID String," +
            "$COL_URL String," +
            "$COL_WIDTH Int)"
