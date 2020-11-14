package kz.lab4.presentation.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseNetworkFragment : Fragment() {

    abstract fun updatePage()

    abstract fun setClickUpdate()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickUpdate()
    }
}