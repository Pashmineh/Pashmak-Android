package app.pashmak.com.pashmak.ui.main

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.ActivityMainBinding
import app.pashmak.com.pashmak.ui.base.BaseActivity
import app.pashmak.com.pashmak.ui.main.adapter.EventListAdapter
import app.pashmak.com.pashmak.util.BEACON_UUID
import app.pashmak.com.pashmak.util.IBEACON_LAYOUT_ID
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import org.altbeacon.beacon.*
import java.util.*
import kotlin.concurrent.thread


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(), BeaconConsumer {

    companion object {

        fun getCallingBundle(): Bundle = Bundle()
    }

    var beaconManager: BeaconManager? = null
    val beaconRegions = Region(BEACON_UUID, listOf(Identifier.fromUuid(UUID.fromString(BEACON_UUID))))

    override val viewModel: MainViewModel by getLazyViewModel()
    override val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

    override fun onViewInitialized(binding: ActivityMainBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel
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

        val states = LocationSettingsStates.fromIntent(intent)

        when (requestCode) {
            MainViewModel.REQUEST_CHECK_LOCATION_SETTINGS -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        viewModel.checkBluetoothSettings()
                    }
                    Activity.RESULT_CANCELED -> { /*The user was asked to change settings, but chose not to*/
                    }
                }
            }
            MainViewModel.REQUEST_ENABLE_BT -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        viewModel.findBeacon()
                    }
                    Activity.RESULT_CANCELED -> { /*The user was asked to change settings, but chose not to*/
                    }
                }
            }
        }
    }

    private fun observeLiveData() {

        observeList()
        observeCheckIn()
        observeSettings()
    }

    private fun observeList() {
        viewModel.eventListLiveData.observe(this, Observer {
            binding.adapter?.swapItems(it)
            binding.eventList.smoothScrollToPosition(0)
            binding.frameShimmer.stopShimmer()
            binding.frameShimmer.visibility = View.GONE
        })
    }

    private fun observeCheckIn() {
        viewModel.checkInLiveData.observe(this, Observer {
            if (it.getContentIfNotHandled() == true) {

                beaconManager = BeaconManager.getInstanceForApplication(this.applicationContext)
                beaconManager!!.beaconParsers.add(
                        BeaconParser().setBeaconLayout(IBEACON_LAYOUT_ID)
                )
                beaconManager!!.bind(this)
            }
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
                        viewModel.checkBluetoothSettings()

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

    private val mMessageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            viewModel.getHomeData()
        }
    }

    override fun onBeaconServiceConnect() {
        beaconManager?.addRangeNotifier { beacons, region ->
            if (beacons?.isNotEmpty() == true) {

                val stringBuilder = StringBuilder()
                for (beac in beacons) {
                    stringBuilder.append("BluetoothName: ${beac.bluetoothName}, typeCode: ${beac.beaconTypeCode}, identifier: ${beac.identifiers}, Manufacturer :${beac.manufacturer}")
                    stringBuilder.appendln()
                }

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Found Beacons")
                builder.setMessage(stringBuilder.toString())
                builder.setPositiveButton(android.R.string.ok, null)
                builder.show()

                beaconManager?.stopRangingBeaconsInRegion(beaconRegions)
            }
        }

        beaconManager?.startRangingBeaconsInRegion(beaconRegions)
    }
}