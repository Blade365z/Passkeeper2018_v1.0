package com.gfxnation.passkeeper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import com.gfxnation.passkeeper.fragments.Const.DATABASE_NAME
import com.gfxnation.passkeeper.fragments.Const.DATABASE_VERSION
import com.gfxnation.passkeeper.fragments.Passwords

/**
 * Created by Anjali Sharma on 5/7/2018.
 */
class DatabaseHelperforPassEntry(context: Context, name: String?,
                                 factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context,
        name, factory, version) {
    val TABLE_ENTRY: String = "table_entry"
    val ID: String = "id"
    val TITLE: String = "title"
    val PASSWORD: String = "password"
    val CATEGORY:String="category"
    val createsql: String = "create table $TABLE_ENTRY($ID INTEGER PRIMARY KEY AUTOINCREMENT, $TITLE TEXT,$CATEGORY TEXT, $PASSWORD TEXT)"
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(createsql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun addpassword(title: String,category:String, password: String): Long {
        var values: ContentValues = ContentValues()
        values.put(TITLE, title)
        values.put(CATEGORY, category)
        values.put(PASSWORD, password)
        return this.writableDatabase.insert(TABLE_ENTRY, null, values)
    }

/*    fun query(): Cursor {

        var db: SQLiteDatabase ?=null

        db!!.rawQuery("select * from $TABLE_ENTRY", null)

        val qb = SQLiteQueryBuilder()
        qb.tables = TABLE_ENTRY
        val projections = arrayOf("Id", "Title", "Password")
        val selectionArgs = arrayOf(TITLE)
        val selections= arrayOf(PASSWORD)
        val cursor = qb.query(db, projections, selections.toString(),selectionArgs, null, null, "Title")
        return cursor
    }*/



    fun updatepassword(id: Int?, title: String, category: String ,password: String): Int {
        var values: ContentValues = ContentValues()
        values.put(TITLE, title)
        values.put(CATEGORY, category)
        values.put(PASSWORD, password)
        return this.writableDatabase.update(TABLE_ENTRY, values, "$ID=?", arrayOf("$id"))
    }

    fun deletepassword(id: Int?) {
        this.writableDatabase.delete(TABLE_ENTRY, "$ID=?", arrayOf("$id"))
    }

    fun getAllPassword(): ArrayList<Passwords> {
        var list: ArrayList<Passwords> = ArrayList()
        var cursor: Cursor = this.writableDatabase.query(TABLE_ENTRY, arrayOf(ID, TITLE,CATEGORY, PASSWORD), null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                var con: Passwords = Passwords()
                con.id = cursor.getInt(0)
                con.title = cursor.getString(1)
                con.category = cursor.getString(2)
                con.password=cursor.getString(3)
                list.add(con)
            } while (cursor.moveToNext())
        }
        return list
    }


    companion object {
        public var instance: DatabaseHelperforPassEntry? = null
        fun getInstance(ctx: Context): DatabaseHelperforPassEntry {
            if (instance == null) {
                instance = DatabaseHelperforPassEntry(ctx, DATABASE_NAME, null, DATABASE_VERSION)
            }
            return instance!!
        }

    }
}

