package app.pashmak.com.pashmak.ui.main.transaction

import android.view.View
import androidx.lifecycle.Observer
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.data.model.transaction.TransactionModel
import app.pashmak.com.pashmak.databinding.FragmentTransactionListBinding
import app.pashmak.com.pashmak.databinding.ItemTransactionBinding
import app.pashmak.com.pashmak.ui.base.BaseFragment
import app.pashmak.com.pashmak.ui.base.ViewModelScope
import app.pashmak.com.pashmak.ui.base.adapter.BaseAdapter

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

        observeLiveData()
        viewModel.getTransactionList()

        binding.adapter = BaseAdapter<TransactionModel, ItemTransactionBinding>(
                R.layout.item_transaction,
                emptyList(),
                viewModel
        )
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
        viewModel.transactionListLiveData.observe(this, Observer {
            binding.adapter?.swapItems(it)
            binding.frameShimmer.stopShimmer()
            binding.frameShimmer.visibility = View.GONE
        })
    }
}