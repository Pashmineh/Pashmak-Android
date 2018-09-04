package app.pashmak.com.pashmak.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

    private fun observeLiveData() {
        viewModel.eventListLiveData.observe(this, Observer {
            binding.adapter?.swapItems(it)
            binding.eventList.smoothScrollToPosition(0)
            binding.frameShimmer.stopShimmer()
            binding.frameShimmer.visibility = View.GONE
        })

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