package kz.lab4.presentation.ui.home

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import kz.lab4.R
import kz.lab4.databinding.ItemTaskBinding
import kz.lab4.entity.todo.TaskUi

class TaskItem(
    val taskUi: TaskUi
) : BindableItem<ItemTaskBinding>() {

    override fun bind(viewBinding: ItemTaskBinding, position: Int) {
        viewBinding.titleTv.text = taskUi.title
        viewBinding.statusTv.text = taskUi.status
        viewBinding.categoryTv.text = taskUi.category
    }

    override fun getLayout(): Int = R.layout.item_task

    override fun initializeViewBinding(view: View): ItemTaskBinding = ItemTaskBinding.bind(view)
}