package kz.lab4.entity.todo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskUi(
    var title: String,
    var description: String,
    var status: String,
    var category: String,
    var duration: Int
) : Parcelable {

    var id: Int? = null

    constructor(
        id: Int,
        title: String,
        description: String,
        status: String,
        category: String,
        duration: Int
    ) : this(title, description, status, category, duration) {
        this.id = id
    }
}