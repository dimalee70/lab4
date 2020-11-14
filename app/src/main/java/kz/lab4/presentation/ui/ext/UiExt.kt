package kz.lab4.presentation.ui.ext

import android.graphics.Color
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBarWithoutButton(layoutView: View, stringId: Int) {
    context?.getText(stringId)?.let {
        Snackbar.make(layoutView, it, Snackbar.LENGTH_SHORT)
            .setTextColor(Color.WHITE)
            .show()
    }
}