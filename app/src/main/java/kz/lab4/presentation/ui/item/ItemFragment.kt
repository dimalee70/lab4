package kz.lab4.presentation.ui.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import kz.lab4.databinding.FragmentItemBinding
import kz.lab4.entity.todo.TaskUi
import kz.lab4.presentation.ui.base.BaseFragment
import kz.lab4.presentation.ui.base.ResultState
import kz.lab4.presentation.ui.home.ItemArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class ItemFragment : BaseFragment<TaskUi, ItemViewModel>() {

    private val args by navArgs<ItemFragmentArgs>()
    private lateinit var itemArgs: ItemArgs

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    override val vm: ItemViewModel by viewModel { parametersOf(itemArgs) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.i("onCreate")
        itemArgs = args.itemArgs
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("onCreateView")
        _binding = FragmentItemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.pageResponse.observe(viewLifecycleOwner, Observer(::onItemChanged))
        vm.loading.observe(viewLifecycleOwner, Observer(::onProgressChanged))
    }

    private fun onItemChanged(response: ResultState<TaskUi>) {
        Timber.i("onItemChanged, response = $response")
        if (response is ResultState.Success) {
            setItemUi(response.data)
        }
    }

    private fun onProgressChanged(isShow: Boolean) {
        Timber.i("onProgressChanged, isShow = $isShow")
        if (isShow)
            showProgress()
        else
            closeProgress()
    }

    private fun setItemUi(taskUi: TaskUi) {
        Timber.i("setItemUi, taskUi = $taskUi")
        binding.titleTv.text = taskUi.title
        binding.categoryTv.text = taskUi.category
        binding.descTv.text = taskUi.description
        binding.durTv.text = taskUi.duration.toString()
        binding.statusTv.text = taskUi.status
    }

    override fun setClickUpdate() {
        Timber.i("setClickUpdate")
    }

    override fun onDestroyView() {
        Timber.i("onDestroyView")
        super.onDestroyView()
        _binding = null
    }

}