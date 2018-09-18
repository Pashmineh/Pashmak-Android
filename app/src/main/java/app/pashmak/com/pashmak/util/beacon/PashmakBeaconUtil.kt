package app.pashmak.com.pashmak.util.beacon

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Intent
import android.os.Build
import android.os.Handler
import androidx.annotation.RequiresApi
import app.pashmak.com.pashmak.util.BEACON_ADDRESS
import javax.inject.Inject
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanSettings
import androidx.fragment.app.FragmentActivity


class PashmakBeaconUtil
@Inject constructor(private val bluetoothAdapter: BluetoothAdapter?) {

    companion object {
        const val REQUEST_ENABLE_BT = 841
        private const val SCANNING_PERIOD = 7000L

    }

    private val beaconHandler = Handler()
    private lateinit var onBeaconFound: () -> Unit
    private lateinit var onFindingFailed: (hasSettingsIssue: Boolean) -> Unit
    private lateinit var mBluetoothLeScanner: BluetoothLeScanner
    private lateinit var mScanCallback: ScanCallback

    fun checkMyBeacon(activity: FragmentActivity, onBeaconFound: () -> Unit, onFindingFailed: (hasSettingsIssue: Boolean) -> Unit) {

        this.onBeaconFound = onBeaconFound
        this.onFindingFailed = onFindingFailed

        if (bluetoothAdapter == null) {
            onFindingFailed.invoke(true)
            return
        } else if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        } else {
            findBeacon()
        }
    }

    /**
     * Get the result of Bluetooth Settings request
     *
     * Should be called in your [Activity.onActivityResult]
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    fun onBeaconSettingsResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_ENABLE_BT) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    findBeacon()
                }
                Activity.RESULT_CANCELED -> {
                    onFindingFailed(true)
                }
            }
        }
    }

    private fun findBeacon() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            mScanCallback =  BtleScanCallback()
            mBluetoothLeScanner = bluetoothAdapter!!.bluetoothLeScanner

            // Note: Filtering does not work the same (or at all) on most devices. It also is unable to
            // search for a mask or anything less than a full UUID.
            // Unless the full UUID of the server is known, manual filtering may be necessary.
            // For example, when looking for a brand of device that contains a char sequence in the UUID
            val scanFilter = ScanFilter.Builder()
                    .setDeviceAddress(BEACON_ADDRESS)
                    .build()

            val settings = ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
                    .build()

            mBluetoothLeScanner.startScan(mutableListOf(scanFilter), settings, mScanCallback)
            beaconHandler.postDelayed({stopScanning(false)}, SCANNING_PERIOD)

        } else {
            // Stops scanning after a pre-defined scan period.
            beaconHandler.postDelayed({
                stopScanning(false)
            }, SCANNING_PERIOD)

            bluetoothAdapter?.startLeScan(mLeScanCallback)
        }
    }

    @Synchronized
    private fun stopScanning(beaconFounded: Boolean) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBluetoothLeScanner.stopScan(mScanCallback);
        }
        else {
            bluetoothAdapter?.stopLeScan(mLeScanCallback)
        }
        if (!beaconFounded)
            onFindingFailed.invoke(false)
    }

    // Device scan callback.
    private val mLeScanCallback = BluetoothAdapter.LeScanCallback { device, _, _ ->

        if (device?.address?.isNotEmpty() == true && device.address == BEACON_ADDRESS) {
            stopScanning(true)
            beaconHandler.removeCallbacksAndMessages(null)
            onBeaconFound.invoke()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private inner class BtleScanCallback internal constructor() : ScanCallback() {

        override fun onScanResult(callbackType: Int, result: ScanResult) {
            addScanResult(result)
        }

        override fun onBatchScanResults(results: List<ScanResult>) {
            for (result in results) {
                addScanResult(result)
            }
        }

        override fun onScanFailed(errorCode: Int) {
            stopScanning(false)
            beaconHandler.removeCallbacksAndMessages(null)
        }

        private fun addScanResult(result: ScanResult) {
            stopScanning(true)
            beaconHandler.removeCallbacksAndMessages(null)
            onBeaconFound.invoke()
        }
    }
}