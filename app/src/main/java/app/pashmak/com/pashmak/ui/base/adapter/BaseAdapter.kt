package app.pashmak.com.pashmak.ui.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.pashmak.com.pashmak.ui.base.BaseViewModel


class BaseAdapter<T, B : ViewDataBinding>
(
        private val layoutId: Int,
        private var items: List<T>,
        private val viewModel: BaseViewModel? = null,
        var onBind: B.(Int) -> Unit = {}
) : RecyclerView.Adapter<BaseViewHolder<T, B>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, B> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: B = DataBindingUtil.inflate(inflater, layoutId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, B>, position: Int) {
        holder.bind(getItem(position), getDefaultViewModel())
        holder.binding.onBind(position)
    }

    override fun getItemCount() = items.size

    fun getDefaultViewModel(): BaseViewModel? = viewModel

    fun getItem(position: Int): T = items[position]

    fun swapItems(newItems: List<T>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    items[oldItemPosition] == newItems[newItemPosition]

            override fun getOldListSize(): Int =
                    items.size

            override fun getNewListSize(): Int =
                    newItems.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    items[oldItemPosition] == newItems[newItemPosition]
        })
        diffResult.dispatchUpdatesTo(this)
        items = newItems.toList()
    }

    interface OnItemClickListener<T> {
        fun onItemClick(view: View, item: T, position: Int = -1)
    }
}