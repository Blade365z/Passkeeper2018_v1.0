package com.gfxnation.passkeeper.fragments

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Anjali Sharma on 5/11/2018.
 */
class Passwords : Parcelable {
    var id : Int = 0
    var title : String = ""
    var password : String = ""
    var category:String=""
    constructor(){

    }
    constructor(id: Int,title: String,category:String,password: String){
        this.id = id
        this.title = title
        this.password= password
        this.category=category
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(this.id)
        parcel.writeString(this.title)
        parcel.writeString(this.password)
        parcel.writeString(this.category)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Passwords(id='$id',title='$title',category='$category',password='$password')"
    }

    protected constructor(`in` : Parcel){
        this.id = `in`.readInt()
        this.title=`in`.readString()
        this.password=`in`.readString()
        this.category=`in`.readString()

    }

    companion object CREATOR : Parcelable.Creator<Passwords> {
        override fun createFromParcel(parcel: Parcel): Passwords {
            return Passwords(parcel)
        }

        override fun newArray(size: Int): Array<Passwords?> {
            return arrayOfNulls(size)
        }
    }
}