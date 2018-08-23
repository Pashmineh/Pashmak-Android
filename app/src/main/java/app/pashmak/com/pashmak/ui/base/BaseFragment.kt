package app.pashmak.com.pashmak.ui.base

//abstract class BaseFragment<V : BaseViewModel, B : ViewDataBinding> : Fragment(), BaseView<V, B> {
//
//    override lateinit var binding: B
//
//    @Inject
//    override lateinit var viewModelFactory: ViewModelProvider.Factory
//
//    /**
//     * Attempt to get viewModel lazily from [viewModelFactory] with the scope of given activity.
//     *
//     * @param activity given scope.
//     * @return T an instance of requested ViewModel.
//     */
//    inline fun <reified T : BaseViewModel> getLazyViewModel(scope: ViewModelScope): Lazy<T> =
//            lazy {
//                when (scope) {
//                    ViewModelScope.ACTIVITY -> ViewModelProviders.of(requireActivity(), viewModelFactory)[T::class.java]
//                    ViewModelScope.FRAGMENT -> ViewModelProviders.of(this, viewModelFactory)[T::class.java]
//                    ViewModelScope.TARGET_FRAGMENT -> ViewModelProviders.of(targetFragment!!, viewModelFactory)[T::class.java]
//                    ViewModelScope.PARENT_FRAGMENT -> ViewModelProviders.of(parentFragment!!, viewModelFactory)[T::class.java]
//                }
//            }
//
//    override fun onAttach(context: Context?) {
//        // we should inject fragment dependencies before invoking super.onAttach()
//        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        // initialize binding
//        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
//        binding.setLifecycleOwner(this)
//
//        // set viewModel as an observer to this activity lifecycle events
//        lifecycle.addObserver(viewModel)
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        // observe viewModel uiActions in order to pass parent activity as argument of uiAction
//        viewModel.activityAction.observe(this, Observer { it?.getContentIfNotHandled()?.invoke(requireActivity()) })
//        viewModel.fragmentAction.observe(this, Observer { it?.getContentIfNotHandled()?.invoke(this) })
//        onViewInitialized(binding)
//    }
//}