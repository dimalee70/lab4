package kz.lab4.presentation.ui.add

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kz.lab4.R
import kz.lab4.databinding.FragmentAddBinding
import kz.lab4.entity.todo.TaskUi
import kz.lab4.presentation.ui.add.AddViewModel.Actions
import kz.lab4.presentation.ui.dialog.DialogFactory
import kz.lab4.presentation.ui.ext.showSnackBarWithoutButton
import kz.lab4.utils.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

open class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val vm: AddViewModel by viewModel()
    private lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("onCreateView")
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.i("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        progressDialog = DialogFactory.getProgressDialog(requireContext())
        vm.action.observe(viewLifecycleOwner, EventObserver(::onActionChanged))
        vm.loading.observe(viewLifecycleOwner, Observer(::onProgressChanged))
        binding.btnAddPage.setOnClickListener {
            addTask()
        }
    }

    private fun onProgressChanged(isShow: Boolean) {
        Timber.i("onProgressChanged, isShow = $isShow")
        if (isShow)
            showProgress()
        else
            closeProgress()
    }

    private fun closeProgress() {
        if (progressDialog.isShowing)
            progressDialog.dismiss()
    }

    protected open fun showProgress() {
        if (!progressDialog.isShowing) {
            progressDialog.show()
        }
    }


    private fun addTask() {
        Timber.i("addTask")
        try {
            val title = binding.title.text.toString()
            val description = binding.description.text.toString()
            val status = binding.status.text.toString()
            val category = binding.category.text.toString()
            val duration = binding.duration.text.toString().toInt()
            val taskUi = TaskUi(
                title = title,
                description = description,
                status = status,
                category = category,
                duration = duration
            )
            vm.addTask(taskUi = taskUi)
        } catch (e: Exception) {
            showSnackBarWithoutButton(
                binding.constraintLayout,
                R.string.snackbar_error
            )
        }

    }

    private fun onActionChanged(response: Actions) {
        Timber.i("onActionChanged, response = $response")
        when (response) {
            Actions.ON_ADD_TASK_SUCCESS -> {
                showSnackBarWithoutButton(
                    binding.constraintLayout,
                    R.string.snackbar_success_added_task
                )
            }
            Actions.ON_ADD_TASK_ERROR -> {
                showSnackBarWithoutButton(
                    binding.constraintLayout,
                    R.string.snackbar_failure_added_task
                )
            }
        }
    }

    override fun onDestroyView() {
        Timber.i("onDestroyView")
        super.onDestroyView()
        _binding = null
    }
}