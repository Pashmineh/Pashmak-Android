package app.pashmak.com.pashmak.ui.main.transaction

import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.FragmentTransactionListBinding
import app.pashmak.com.pashmak.ui.base.BaseFragment
import app.pashmak.com.pashmak.ui.base.ViewModelScope

class TransactionListFragment: BaseFragment<TransactionListViewModel, FragmentTransactionListBinding>()
{
    companion object {

        const val TAG = "TransactionListFragment"
        fun newInstance() = TransactionListFragment()
    }

    override val viewModel: TransactionListViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)
    override val layoutId: Int get() = R.layout.fragment_transaction_list

    override fun onViewInitialized(binding: FragmentTransactionListBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel

        viewModel.getDebtList()
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