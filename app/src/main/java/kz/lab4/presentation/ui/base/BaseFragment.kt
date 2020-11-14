package kz.lab4.presentation.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import kz.lab4.presentation.ui.dialog.DialogFactory
import timber.log.Timber

abstract class BaseFragment<T, VM: BaseViewModel<T>> : BaseNetworkFragment(){

    open val vm: VM? = null

    private lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState === null){
            vm?.setArguments(arguments)
            loadData()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.i("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        progressDialog = DialogFactory.getProgressDialog(requireContext())
    }
    override fun updatePage() {
        Timber.i("updatePage")
        loadData()
    }

    private fun loadData() {
        Timber.i("loadData")
        vm?.loadData()
    }

    protected open fun showProgress() {
        if (!progressDialog.isShowing) {
            progressDialog.show()
        }
    }

    protected fun closeProgress() {
        if (progressDialog.isShowing)
            progressDialog.dismiss()
    }

}