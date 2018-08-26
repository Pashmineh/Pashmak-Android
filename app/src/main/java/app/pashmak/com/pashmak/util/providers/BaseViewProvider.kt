package app.pashmak.com.pashmak.util.providers

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface BaseViewProvider
{
    fun <T : View>findView(activity: FragmentActivity, @IdRes id: Int): T?

    fun <T : View>findView(fragment: Fragment, @IdRes id: Int): T?
}