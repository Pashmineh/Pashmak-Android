package app.pashmak.com.pashmak.ui.base


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import app.pashmak.com.pashmak.util.livedata.ActivityActionLiveData
import app.pashmak.com.pashmak.util.livedata.FragmentActionLiveData
import io.reactivex.disposables.CompositeDisposable

/**
 * All of ViewModels should be inherited from [BaseViewModel]
 */
abstract class BaseViewModel()
    : ViewModel(), LifecycleObserver {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val activityAction = ActivityActionLiveData()
    val fragmentAction = FragmentActionLiveData()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    /**
     * We can use lifeCycle in inherited classes if we need
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {}

    /**
     * We can use lifeCycle in inherited classes if we need
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {}
}