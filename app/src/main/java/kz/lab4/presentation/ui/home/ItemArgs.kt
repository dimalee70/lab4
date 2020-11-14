package kz.lab4.presentation.ui.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemArgs(
    val taskId: Int
) : Parcelable