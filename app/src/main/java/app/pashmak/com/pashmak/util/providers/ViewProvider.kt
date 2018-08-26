package app.pashmak.com.pashmak.util.providers

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

class ViewProvider
@Inject constructor() : BaseViewProvider {
    override fun <T : View> findView(activity: FragmentActivity, id: Int): T? {
        return activity.findViewById(id)
    }

    override fun <T : View> findView(fragment: Fragment, id: Int): T? {
        return fragment.view?.findViewById(id)
    }
}