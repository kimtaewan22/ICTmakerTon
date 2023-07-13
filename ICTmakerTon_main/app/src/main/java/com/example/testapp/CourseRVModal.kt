package com.example.testapp

import android.os.Parcel
import android.os.Parcelable

data class CourseRVModal(
    // on below line we are creating a two variable
    // one for course name and other for course image.
    var courseName: String,
    var courseImg: Int,
    var stateCount: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(courseName)
        parcel.writeInt(courseImg)
        parcel.writeString(stateCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseRVModal> {
        override fun createFromParcel(parcel: Parcel): CourseRVModal {
            return CourseRVModal(parcel)
        }

        override fun newArray(size: Int): Array<CourseRVModal?> {
            return arrayOfNulls(size)
        }
    }
}