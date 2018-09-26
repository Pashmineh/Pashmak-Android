package app.pashmak.com.pashmak.ui.main.checkin

import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.FragmentCheckinBinding
import app.pashmak.com.pashmak.ui.base.BaseFragment
import app.pashmak.com.pashmak.ui.base.ViewModelScope

class CheckInFragment: BaseFragment<CheckInViewModel, FragmentCheckinBinding>()
{
    companion object {

        const val TAG = "CheckInFragment"
        fun newInstance() = CheckInFragment()
    }

    override val viewModel: CheckInViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)
    override val layoutId: Int get() = R.layout.fragment_checkin
}