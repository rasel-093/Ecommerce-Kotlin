package com.rasel.ecommerce_kotlin.model

import android.os.Parcel
import android.os.Parcelable

data class ItemModel(
    val title: String ="",
    val price: Double = 0.0,
    val description: String = "",
    val picUrl: ArrayList<String> = ArrayList(),
    var model: ArrayList<String> = ArrayList(),
    var rating: Double = 0.0,
    var numberInCart: Int = 0,
    var showRecommended: Boolean = false,
    var categoryId: String = ""
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
        dest.writeDouble(price)
        dest.writeStringList(picUrl)
        dest.writeStringList(model)
        dest.writeDouble(rating)
        dest.writeInt(numberInCart)
        dest.writeByte(if (showRecommended) 1 else 0)
        dest.writeString(categoryId)
    }

    companion object CREATOR : Parcelable.Creator<ItemModel> {
        override fun createFromParcel(parcel: Parcel): ItemModel {
            return ItemModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemModel?> {
            return arrayOfNulls(size)
        }
    }

}