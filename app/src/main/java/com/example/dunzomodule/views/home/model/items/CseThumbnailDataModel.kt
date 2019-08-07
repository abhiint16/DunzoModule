package com.example.dunzomodule.views.home.model.items

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class CseThumbnailDataModel(
    @SerializedName("width")
    var width: String?,
    @SerializedName("height")
    var height: String?,
    @SerializedName("src")
    var src: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(width)
        parcel.writeString(height)
        parcel.writeString(src)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CseThumbnailDataModel> {
        override fun createFromParcel(parcel: Parcel): CseThumbnailDataModel {
            return CseThumbnailDataModel(parcel)
        }

        override fun newArray(size: Int): Array<CseThumbnailDataModel?> {
            return arrayOfNulls(size)
        }
    }
}