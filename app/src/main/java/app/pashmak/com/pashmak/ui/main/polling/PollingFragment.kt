package app.pashmak.com.pashmak.ui.main.polling


import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.FragmentPollingBinding
import app.pashmak.com.pashmak.ui.base.BaseFragment
import app.pashmak.com.pashmak.ui.base.ViewModelScope

class PollingFragment: BaseFragment<PollingViewModel, FragmentPollingBinding>()
{
    override val viewModel: PollingViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)
    override val layoutId: Int get() = R.layout.fragment_polling

    override fun onViewInitialized(binding: FragmentPollingBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.Dark_tangerine)
//        }
    }
}