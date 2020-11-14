package kz.lab4.presentation.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import kz.lab4.R

object DialogFactory {
    fun getProgressDialog(context: Context): Dialog =
        AlertDialog.Builder(context).setCancelable(false).setView(R.layout.dialog_progress).create()
}