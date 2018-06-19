package com.gfxnation.passkeeper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.gfxnation.passkeeper.fragments.MasterPassword


/**
 * Created by abaruah on 27/3/18.
 */
class  DatabaseHelper(context:Context):SQLiteOpenHelper(context,dbname,factory, version){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table user(id INTEGER PRIMARY KEY autoincrement NOT NULL,name varchar(30),email varchar(100),pin varchar(20));")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun insertUserData(name:String,email:String,pin:String)
    {
        val db:SQLiteDatabase=writableDatabase
        val values:ContentValues= ContentValues()
        values.put("name",name)
        values.put("email",email)
        values.put("pin",pin)
        db.insert("user",null,values)
        db.close()
    }
    fun ussrPresent(email: String, pin: String): Boolean {
        val db=writableDatabase
        val query="select * from user where email = '$email' AND pin = '$pin'"
        val cursor=db.rawQuery(query,null)
        if (cursor.count<=0)
        {
            cursor.close()
            return false
        }
        else {
            return true
        }
    }
    fun updateUserData(pin: String,email: String){
        val db=this.writableDatabase
        val values:ContentValues= ContentValues()
        values.put("pin",pin)
        db.update("user",values,"email = ? ", arrayOf(email))
        db.close()

    }


    companion object{
        internal val dbname = "userDB"
        internal val factory = null
        internal val version = 1
    }
    fun usermailPresent(email: String): Boolean {
        val db=writableDatabase
        val query="select * from user where email = '$email'"
        val cursor=db.rawQuery(query,null)
        if (cursor.count<=0)
        {
            cursor.close()
            return false
        }
        else {
            return true
        }
    }

}