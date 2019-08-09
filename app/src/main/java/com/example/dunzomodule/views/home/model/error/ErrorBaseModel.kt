package com.example.dunzomodule.views.home.model.error

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ErrorBaseModel(
    @SerializedName("code")
    var code: Int? = null,
    @SerializedName("message")
    var message: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(code)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ErrorBaseModel> {
        override fun createFromParcel(parcel: Parcel): ErrorBaseModel {
            return ErrorBaseModel(parcel)
        }

        override fun newArray(size: Int): Array<ErrorBaseModel?> {
            return arrayOfNulls(size)
        }
    }
}