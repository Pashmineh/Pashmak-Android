package app.pashmak.com.pashmak.ui.main.messages

import android.view.View
import androidx.lifecycle.Observer
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.data.model.message.MessageModel
import app.pashmak.com.pashmak.databinding.FragmentMessageListBinding
import app.pashmak.com.pashmak.databinding.ItemMessageBinding
import app.pashmak.com.pashmak.ui.base.BaseFragment
import app.pashmak.com.pashmak.ui.base.ViewModelScope
import app.pashmak.com.pashmak.ui.base.adapter.BaseAdapter


class MessageListFragment : BaseFragment<MessageListViewModel, FragmentMessageListBinding>() {
    companion object {

        const val TAG = "MessageListFragment"
        fun newInstance() = MessageListFragment()
    }

    override val viewModel: MessageListViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)
    override val layoutId: Int get() = R.layout.fragment_message_list

    override fun onViewInitialized(binding: FragmentMessageListBinding) {
        super.onViewInitialized(binding)

        binding.viewModel = viewModel
        binding.adapter = BaseAdapter<MessageModel, ItemMessageBinding>(
                R.layout.item_message,
                emptyList(),
                viewModel
        )

        observeLiveData()
        viewModel.getMessageList()
    }

    override fun onResume() {
        super.onResume()
        binding.frameShimmer.startShimmer()
    }

    override fun onPause() {
        binding.frameShimmer.stopShimmer()
        super.onPause()
    }

    private fun observeLiveData(){
        viewModel.messageListLiveData.observe(this, Observer {
            binding.adapter?.swapItems(it)
            binding.frameShimmer.startShimmer()
            binding.frameShimmer.visibility = View.GONE
        })
    }
}