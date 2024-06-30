package com.example.final_tp3.data

import android.os.Parcel
import android.os.Parcelable

data class Menu(
    val Item: String = "",
    val Price: Double = 0.0,
    val Saved: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Item)
        parcel.writeDouble(Price)
        parcel.writeByte(if (Saved) 1 else 0)
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