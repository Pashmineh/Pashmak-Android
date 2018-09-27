package app.pashmak.com.pashmak.ui.main.checkin

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.data.model.checkin.CheckInResponse
import app.pashmak.com.pashmak.databinding.FragmentCheckinBinding
import app.pashmak.com.pashmak.databinding.ItemCheckinBinding
import app.pashmak.com.pashmak.ui.base.BaseFragment
import app.pashmak.com.pashmak.ui.base.ViewModelScope
import app.pashmak.com.pashmak.ui.base.adapter.BaseAdapter
import app.pashmak.com.pashmak.ui.main.home.HomeViewModel
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes

class CheckInFragment: BaseFragment<CheckInViewModel, FragmentCheckinBinding>()
{
    companion object {

        const val TAG = "CheckInFragment"
        fun newInstance() = CheckInFragment()
    }

    override val viewModel: CheckInViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)
    override val layoutId: Int get() = R.layout.fragment_checkin

    override fun onViewInitialized(binding: FragmentCheckinBinding) {
        super.onViewInitialized(binding)

        binding.viewModel = viewModel
        binding.adapter = BaseAdapter<CheckInResponse, ItemCheckinBinding>(
                R.layout.item_checkin,
                emptyList(),
                viewModel
        )

        observeLiveDatas()
        viewModel.getCheckInList()
    }

    override fun onResume() {
        super.onResume()
        binding.frameShimmer.startShimmer()
    }

    override fun onPause() {
        binding.frameShimmer.stopShimmer()
        super.onPause()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        viewModel.permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        viewModel.pashmakBeaconUtil.onBeaconSettingsResult(requestCode, resultCode, data)

        when (requestCode) {
            CheckInViewModel.REQUEST_CHECK_LOCATION_SETTINGS -> {
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

    private fun observeLiveDatas(){
        observeList()
        observeSettings()
        observeMessages()
    }

    private fun observeList(){
        viewModel.checkInListLiveDatas.observe(this, Observer {
            binding.adapter?.swapItems(it)
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

                val result = LocationServices.getSettingsClient(requireContext()).checkLocationSettings(builder.build())
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
                                            requireActivity(),
                                            CheckInViewModel.REQUEST_CHECK_LOCATION_SETTINGS)
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
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }
}