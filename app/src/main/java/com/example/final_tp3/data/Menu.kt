package com.example.final_tp3.data

import android.os.Parcel
import android.os.Parcelable

data class Menu(
    val idRestaurant: String = "",
    val item: String = "",
    val price: Double = 0.0,
    val saved: Boolean = false,
    val img: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: ""

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idRestaurant)
        parcel.writeString(item)
        parcel.writeDouble(price)
        parcel.writeByte(if (saved) 1 else 0)
        parcel.writeString(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Menu> {
        override fun createFromParcel(parcel: Parcel): Menu {
            return Menu(parcel)
        }

        override fun newArray(size: Int): Array<Menu?> {
            return arrayOfNulls(size)
        }
    }
}