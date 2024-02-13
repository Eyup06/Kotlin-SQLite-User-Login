package com.eyupyilmaz.kotlinuserlogin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,"User_data",null,1) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE User_data (username TEXT PRIMARY KEY, password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS User_data")
    }

    fun insertData(username : String, password : String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username",username)
        contentValues.put("password",password)
        val result = db.insert("User_data",null,contentValues)

        if (result == -1 .toLong())
        {
            return false
        }
        return true
    }

    fun checkUserPass(username : String, password: String): Boolean {
        val db = this.writableDatabase
        val query = "SELECT * FROM User_data WHERE username= '$username' and password= '$password'"
        val cursor = db.rawQuery(query,null)
        if (cursor.count <= 0)
        {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }
}