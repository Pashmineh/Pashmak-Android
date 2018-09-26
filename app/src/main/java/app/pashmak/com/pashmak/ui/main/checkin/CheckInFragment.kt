package app.pashmak.com.pashmak.ui.main.checkin

import android.view.View
import androidx.lifecycle.Observer
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.data.model.checkin.CheckInResponse
import app.pashmak.com.pashmak.databinding.FragmentCheckinBinding
import app.pashmak.com.pashmak.databinding.ItemCheckinBinding
import app.pashmak.com.pashmak.ui.base.BaseFragment
import app.pashmak.com.pashmak.ui.base.ViewModelScope
import app.pashmak.com.pashmak.ui.base.adapter.BaseAdapter

class CheckInFragment: BaseFragment<CheckInViewModel, FragmentCheckinBinding>()
{
    companion object {

        const val TAG = "CheckInFragment"
        fun newInstance() = CheckInFragment()
    }

    override val viewModel: CheckInViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)
    override val layoutId: Int get() = R.layout.fragment_checkin

    override fun onViewInitialized(binding: FragmentCheckinBinding) {
        super.onViewInitialized(binding)

        binding.viewModel = viewModel
        binding.adapter = BaseAdapter<CheckInResponse, ItemCheckinBinding>(
                R.layout.item_checkin,
                emptyList(),
                viewModel
        )

        observeLiveDatas()
        viewModel.getCheckInList()
    }

    override fun onResume() {
        super.onResume()
        binding.frameShimmer.startShimmer()
    }

    override fun onPause() {
        binding.frameShimmer.stopShimmer()
        super.onPause()
    }

    private fun observeLiveDatas(){
        viewModel.checkInListLiveDatas.observe(this, Observer {
            binding.adapter?.swapItems(it)
            binding.frameShimmer.stopShimmer()
            binding.frameShimmer.visibility = View.GONE
        })
    }
}