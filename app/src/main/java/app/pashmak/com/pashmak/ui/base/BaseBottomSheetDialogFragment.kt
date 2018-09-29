package app.pashmak.com.pashmak.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseBottomSheetDialogFragment<V : BaseViewModel, B : ViewDataBinding> : BottomSheetDialogFragment(), BaseView<V, B> {

    override lateinit var binding: B

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    inline fun <reified T : BaseViewModel> getLazyViewModel(scope: ViewModelScope): Lazy<T> =
            lazy {
                when (scope) {
                    ViewModelScope.ACTIVITY -> ViewModelProviders.of(requireActivity(), viewModelFactory)[T::class.java]
                    ViewModelScope.FRAGMENT -> ViewModelProviders.of(this, viewModelFactory)[T::class.java]
                    ViewModelScope.PARENT_FRAGMENT -> ViewModelProviders.of(this.parentFragment!!, viewModelFactory)[T::class.java]
                    ViewModelScope.TARGET_FRAGMENT -> ViewModelProviders.of(this.targetFragment!!, viewModelFactory)[T::class.java]
                }
            }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setLifecycleOwner(this)
        lifecycle.addObserver(viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.activityAction.observe(this, Observer { it?.getContentIfNotHandled()?.invoke(requireActivity()) })
        viewModel.fragmentAction.observe(this, Observer { it?.getContentIfNotHandled()?.invoke(this) })
        onViewInitialized(binding)
    }
}
