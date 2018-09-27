package app.pashmak.com.pashmak.ui.main.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.FragmentHomeBinding
import app.pashmak.com.pashmak.ui.base.BaseFragment
import app.pashmak.com.pashmak.ui.base.ViewModelScope
import app.pashmak.com.pashmak.ui.main.adapter.EventListAdapter

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    companion object {

        const val TAG = "HomeFragment"

        fun newInstance() = HomeFragment()
    }

    override val viewModel: HomeViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)
    override val layoutId: Int get() = R.layout.fragment_home

    override fun onViewInitialized(binding: FragmentHomeBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel
        binding.viewState = viewModel.viewState

        binding.adapter = EventListAdapter(
                emptyList(),
                viewModel
        )
        observeLiveData()
    }

    override fun onResume() {
        super.onResume()
        binding.frameShimmer.startShimmer()
    }

    override fun onPause() {
        binding.frameShimmer.stopShimmer()
        super.onPause()
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(mMessageReceiver)
        super.onDestroy()
    }

    private fun observeLiveData() {
        observeList()
    }

    private fun observeList() {
        viewModel.eventListLiveData.observe(this, Observer {
            binding.adapter?.swapItems(it)
            binding.eventList.smoothScrollToPosition(0)
            binding.frameShimmer.stopShimmer()
            binding.frameShimmer.visibility = View.GONE
        })
    }

    private val mMessageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            viewModel.getHomeData()
        }
    }
}