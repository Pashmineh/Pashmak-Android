package app.pashmak.com.pashmak.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.data.model.home.Event
import app.pashmak.com.pashmak.databinding.ItemEventBinding
import app.pashmak.com.pashmak.ui.main.MainViewModel

class EventListAdapter(
        private var items: List<Event>,
        private val viewModel: MainViewModel
) : RecyclerView.Adapter<EventViewHolder>() {

    companion object {
        private const val EVENT_ITEM = 1
        private const val EVENT_FOOTER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == EVENT_FOOTER) {
            val view = inflater.inflate(R.layout.item_event_space, parent, false)
            EventViewHolder(view)
        } else {
            val binding: ItemEventBinding = DataBindingUtil.inflate(inflater, R.layout.item_event, parent, false)
            EventViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        if (position != items.size) {
            holder.bind(position, items[position], viewModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size)
            EVENT_FOOTER
        else
            EVENT_ITEM
    }

    override fun getItemCount(): Int = items.size + 1

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