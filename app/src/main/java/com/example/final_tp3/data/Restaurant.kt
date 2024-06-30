package com.example.final_tp3.data

import android.os.Parcel
import android.os.Parcelable

data class Restaurant(
    val Name: String = "",
    val Type: String = "",
    val Rate: Double = 0.0,
    val Img: String = "",
    val menu: List<Menu> = listOf()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.createTypedArrayList(Menu.CREATOR) ?: listOf()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Name)
        parcel.writeString(Type)
        parcel.writeDouble(Rate)
        parcel.writeString(Img)
        parcel.writeTypedList(menu)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Restaurant> {
        override fun createFromParcel(parcel: Parcel): Restaurant {
            return Restaurant(parcel)
        }

        override fun newArray(size: Int): Array<Restaurant?> {
            return arrayOfNulls(size)
        }
    }
}