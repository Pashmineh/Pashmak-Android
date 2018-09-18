package app.pashmak.com.pashmak.ui.main

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.ActivityMainBinding
import app.pashmak.com.pashmak.ui.base.BaseActivity
import app.pashmak.com.pashmak.ui.main.adapter.EventListAdapter
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    companion object {

        fun getCallingBundle(): Bundle = Bundle()
    }

    override val viewModel: MainViewModel by getLazyViewModel()
    override val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

    override fun onViewInitialized(binding: ActivityMainBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel
        binding.viewState = viewModel.viewState
        binding.adapter = EventListAdapter(
                emptyList(),
                viewModel
        )
        observeLiveData()
    }

    override fun onResume() {
        super.onResume()
        binding.frameShimmer.startShimmer()
    }

    override fun onPause() {
        binding.frameShimmer.stopShimmer()
        super.onPause()
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver)
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        viewModel.permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        viewModel.pashmakBeaconUtil.onBeaconSettingsResult(requestCode, resultCode, data)

        when (requestCode) {
            MainViewModel.REQUEST_CHECK_LOCATION_SETTINGS -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        viewModel.checkBeaconState()
                    }
                    Activity.RESULT_CANCELED -> { /*The user was asked to change settings, but chose not to*/
                    }
                }
            }
        }
    }

    private fun observeLiveData() {
        observeList()
        observeSettings()
        observeMessages()
    }

    private fun observeList() {
        viewModel.eventListLiveData.observe(this, Observer {
            binding.adapter?.swapItems(it)
            binding.eventList.smoothScrollToPosition(0)
            binding.frameShimmer.stopShimmer()
            binding.frameShimmer.visibility = View.GONE
        })
    }

    private fun observeSettings() {
        viewModel.settingsLiveData.observe(this, Observer {
            if (it.getContentIfNotHandled() == true) {
                val builder = LocationSettingsRequest.Builder()
                        .addLocationRequest(
                                LocationRequest().apply {
                                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                                }
                        )

                val result = LocationServices.getSettingsClient(this).checkLocationSettings(builder.build())
                result.addOnCompleteListener { task ->
                    try {
                        val response = task.getResult(ApiException::class.java)
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        viewModel.checkBeaconState()

                    } catch (exception: ApiException) {
                        when (exception.statusCode) {

                            LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                                // Location settings are not satisfied. But could be fixed by showing the
                                // user a dialog.
                                try {
                                    // Cast to a resolvable exception.
                                    val resolvable = exception as ResolvableApiException
                                    // Show the dialog by calling startResolutionForResult(),
                                    // and check the result in onActivityResult().
                                    resolvable.startResolutionForResult(
                                            this@MainActivity,
                                            MainViewModel.REQUEST_CHECK_LOCATION_SETTINGS)
                                } catch (e: IntentSender.SendIntentException) {
                                    // Ignore the error.
                                } catch (e: ClassCastException) {
                                    // Ignore, should be an impossible error.
                                }
                            }
                            LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                                // Location settings are not satisfied. However, we have no way to fix the
                                // settings so we won't show the dialog.
                            }
                        }
                    }
                }
            }
        })
    }

    private fun observeMessages(){
        viewModel.messageLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private val mMessageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            viewModel.getHomeData()
        }
    }
}