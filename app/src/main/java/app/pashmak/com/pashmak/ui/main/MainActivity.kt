package app.pashmak.com.pashmak.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.ActivityMainBinding
import app.pashmak.com.pashmak.ui.base.BaseActivity

class MainActivity: BaseActivity<MainViewModel, ActivityMainBinding>()
{
    companion object {
        fun getCallingBundle(): Bundle = Bundle()
    }

    override val viewModel: MainViewModel by getLazyViewModel()
    override val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

    override fun onViewInitialized(binding: ActivityMainBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel
        binding.adapter = EventListAdapter(
                emptyList(),
                viewModel
        ) { position = it }
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.eventListLiveData.observe(this, Observer {
            binding.adapter?.swapItems(it)
        })
    }
}