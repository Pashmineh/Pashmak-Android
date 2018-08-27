package app.pashmak.com.pashmak.ui.main

import androidx.recyclerview.widget.DiffUtil
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.data.model.home.Event
import app.pashmak.com.pashmak.databinding.ItemEventBinding
import app.pashmak.com.pashmak.ui.base.adapter.BaseAdapter

class EventListAdapter(
        private var items: List<Event>,
        private val viewModel: MainViewModel,
        onBind: ItemEventBinding.(Int) -> Unit = {}
) : BaseAdapter<Event, ItemEventBinding>(onBind) {

    override fun getDefaultViewModel(): MainViewModel = viewModel

    override fun getItem(position: Int): Event = items[position]

    override fun getLayoutId(position: Int): Int = R.layout.item_event

    override fun getItemCount(): Int = items.size

    fun swapItems(newItems: List<Event>) {
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
}