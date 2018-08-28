package app.pashmak.com.pashmak.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.pashmak.com.pashmak.BR
import app.pashmak.com.pashmak.data.model.home.Event
import app.pashmak.com.pashmak.databinding.ItemEventBinding
import app.pashmak.com.pashmak.ui.main.MainViewModel

class EventViewHolder: RecyclerView.ViewHolder
{
    var binding: ItemEventBinding? = null

    constructor(binding: ItemEventBinding): super(binding.root){ this.binding = binding }
    constructor(view: View): super(view)

    fun bind(position: Int, item: Event?, viewModel: MainViewModel){
        binding?.setVariable(BR.viewModel, viewModel)
        binding?.setVariable(BR.item, item)
        binding?.setVariable(BR.position, position)
        binding?.executePendingBindings()
    }
}