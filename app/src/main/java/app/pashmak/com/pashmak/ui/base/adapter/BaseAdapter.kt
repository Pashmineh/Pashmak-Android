package app.pashmak.com.pashmak.ui.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import app.pashmak.com.pashmak.ui.base.BaseViewModel


abstract class BaseAdapter<T, B : ViewDataBinding>(var onBind: B.(Int) -> Unit = {}) : RecyclerView.Adapter<BaseViewHolder<T, B>>() {

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, B> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: B = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, B>, position: Int) {
        holder.bind(getItem(position), getDefaultViewModel())
        holder.binding.onBind(position)
    }

    abstract fun getDefaultViewModel(): BaseViewModel?

    abstract fun getItem(position: Int): T

    abstract fun getLayoutId(position: Int): Int

    interface OnItemClickListener<T> {
        fun onItemClick(view: View, item: T, position: Int = -1)
    }
}