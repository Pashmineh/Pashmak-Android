package app.pashmak.com.pashmak.ui.main.messages

import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.FragmentMessageListBinding
import app.pashmak.com.pashmak.ui.base.BaseFragment
import app.pashmak.com.pashmak.ui.base.ViewModelScope


class MessageListFragment: BaseFragment<MessageListViewModel, FragmentMessageListBinding>()
{
    companion object {

        const val TAG = "MessageListFragment"
        fun newInstance() = MessageListFragment()
    }

    override val viewModel: MessageListViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)
    override val layoutId: Int get() = R.layout.fragment_message_list

    override fun onViewInitialized(binding: FragmentMessageListBinding) {
        super.onViewInitialized(binding)

        binding.viewModel = viewModel
//        binding.adapter = BaseAdapter<>
    }

    override fun onResume() {
        super.onResume()
        binding.frameShimmer.startShimmer()
    }

    override fun onPause() {
        binding.frameShimmer.stopShimmer()
        super.onPause()
    }
}