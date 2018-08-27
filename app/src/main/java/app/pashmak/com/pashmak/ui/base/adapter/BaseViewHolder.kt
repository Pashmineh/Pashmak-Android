package app.pashmak.com.pashmak.ui.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import app.pashmak.com.pashmak.BR
import app.pashmak.com.pashmak.ui.base.BaseViewModel

class BaseViewHolder<in T, out B: ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T, viewModel: BaseViewModel? = null) {
        if (viewModel != null) binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}