package kz.lab4.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import kz.lab4.databinding.FragmentHomeBinding
import kz.lab4.entity.todo.TaskUi
import kz.lab4.presentation.ui.base.BaseFragment
import kz.lab4.presentation.ui.base.ResultState
import kz.lab4.utils.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment<List<TaskUi>, HomeViewModel>() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override val vm: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("onCreateView")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("onViewCreated")
        initSwipeRefresh()
        binding.btnAddPage.setOnClickListener {
            openAddScreen()
        }
        vm.pageResponse.observe(viewLifecycleOwner, Observer(::onTaskChanged))
        vm.itemArgs.observe(viewLifecycleOwner, EventObserver(::onArgsChanged))
        vm.loading.observe(viewLifecycleOwner, Observer(::onProgressChanged))
    }

    private fun openAddScreen() {
        Timber.i("openAddScreen")
        val action = HomeFragmentDirections.actionToAdd()
        with(findNavController()) {
            currentDestination?.getAction(action.actionId)
                ?.let { navigate(action) }
        }
    }

    private fun onProgressChanged(isShow: Boolean) {
        Timber.i("onProgressChanged, isShow = $isShow")
        if (isShow)
            showProgress()
        else
            closeProgress()
    }

    private fun onArgsChanged(itemArgs: ItemArgs) {
        Timber.i("onArgsChanged")
        val action = HomeFragmentDirections.actionToItem(itemArgs)
        with(findNavController()) {
            currentDestination?.getAction(action.actionId)
                ?.let { navigate(action) }
        }
    }

    private fun onTaskChanged(response: ResultState<List<TaskUi>>) {
        Timber.i("onTaskChanged, response = $response")
        binding.swipeRefresh.isRefreshing = false
        if (response is ResultState.Success) {
            val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
                setOnItemClickListener { item, view ->
                    vm.onClickTask(item as TaskItem)
                }
                if (response.data.isEmpty()) {
                    clear()
                    no_todo.visibility = View.VISIBLE
                } else {
                    no_todo.visibility = View.GONE
                    addAll(response.data.map { TaskItem(taskUi = it) })
                }
            }
            binding.tasksRv.adapter = groupAdapter
        }
    }

    private fun initSwipeRefresh() {
        Timber.i("initSwipeRefresh")
        binding.swipeRefresh.setOnRefreshListener {
            vm.loadData()
        }
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