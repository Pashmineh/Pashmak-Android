package app.pashmak.com.pashmak.ui.main.polling

import androidx.lifecycle.Observer
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.data.model.polling.PollItem
import app.pashmak.com.pashmak.data.model.polling.PollModel
import app.pashmak.com.pashmak.databinding.FragmentPollingBinding
import app.pashmak.com.pashmak.databinding.ItemPollingBinding
import app.pashmak.com.pashmak.databinding.ItemPollingCandidBinding
import app.pashmak.com.pashmak.ui.base.BaseFragment
import app.pashmak.com.pashmak.ui.base.ViewModelScope
import app.pashmak.com.pashmak.ui.base.adapter.BaseAdapter


class PollingFragment: BaseFragment<PollingViewModel, FragmentPollingBinding>()
{
    companion object {

        const val TAG = "PollingFragment"

        fun newInstance() = PollingFragment()
    }

    override val viewModel: PollingViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)
    override val layoutId: Int get() = R.layout.fragment_polling

    override fun onViewInitialized(binding: FragmentPollingBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel
        binding.adapter =
                BaseAdapter<PollModel, ItemPollingBinding>(
                        R.layout.item_polling,
                        emptyList(),
                        viewModel)
                {
                    adapter = BaseAdapter<PollItem, ItemPollingCandidBinding>(
                            R.layout.item_polling_candid,
                            item!!.pollItemSet,
                            viewModel
                    )
                }

        subscribeLiveData()
        viewModel.getPolls()

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.Dark_tangerine)
//        }
    }

    private fun subscribeLiveData(){
        viewModel.pollListLiveData.observe(this, Observer {
            binding.adapter?.swapItems(it)
        })
    }
}