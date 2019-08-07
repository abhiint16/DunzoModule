package com.example.dunzomodule.views.home.model.items

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class CseImageDataModel(
    @SerializedName("src")
    var src: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(src)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CseImageDataModel> {
        override fun createFromParcel(parcel: Parcel): CseImageDataModel {
            return CseImageDataModel(parcel)
        }

        override fun newArray(size: Int): Array<CseImageDataModel?> {
            return arrayOfNulls(size)
        }
    }
}