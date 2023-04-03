package az.sharif.bippyteam

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import az.sharif.bippyteam.databinding.FragmentDiscoveryBinding
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.util.*

class DiscoveryFragment : Fragment() {
    private lateinit var binding: FragmentDiscoveryBinding
    private var locationManager: LocationManager? = null
    var locationListener: LocationListener? = null
    var permissionLauncher: ActivityResultLauncher<String>? = null
    private lateinit var viewModel: DiscoveryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[DiscoveryViewModel::class.java]

        addObservers()

        viewModel.getAllServices()

        return inflater.inflate(R.layout.fragment_discovery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiscoveryBinding.bind(view)

        registerLauncher()
        LocationUtils()
        localeName()
    }

    private fun registerLauncher() {
        permissionLauncher = registerForActivityResult<String, Boolean>(
            ActivityResultContracts.RequestPermission()
        ) { result: Boolean ->
            if (result) {
                //Permission granted
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    locationManager!!.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 0, 0f,
                        locationListener!!
                    )
                }
            } else {
                //Permission denied
                Toast.makeText(context, "Permission needed!", Toast.LENGTH_SHORT).show()
            }
        }
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                Snackbar.make(
                    binding.root,
                    "Permission Needed for maps",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction(
                    "Give Permission"
                ) { v: View? ->
                    permissionLauncher!!.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                }.show()
            } else {
                permissionLauncher!!.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun LocationUtils() {
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = LocationListener { }
    }

    private fun getLastKnownLocation(): Location? {
        if (ContextCompat.checkSelfPermission(
                requireActivity().applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0f,
                locationListener!!
            )
        } else {
            //Permission denied
            Toast.makeText(context, "Permission needed!", Toast.LENGTH_SHORT).show()
        }
        return locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
    }

    private fun mapString() {
        val latitude = getLastKnownLocation()!!.latitude
        val longitude = getLastKnownLocation()!!.longitude
        try {
            val geo = Geocoder(requireContext(), Locale.getDefault())
            var addresses: List<Address>? = null
            try {
                addresses = geo.getFromLocation(latitude, longitude, 1)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (addresses == null) return
            if (addresses.isEmpty()) {
                Toast.makeText(context, "Waiting for Location", Toast.LENGTH_SHORT).show()
            } else {
                val name =
                    addresses[0].locality + "," + addresses[0].subLocality + "," + addresses[0].countryName
                binding.currentLocation.text = name
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun localeName() {
        object : CountDownTimer(10, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                mapString()
            }
        }.start()
    }

    fun addObservers() {
        viewModel.servicesLiveData.observe(viewLifecycleOwner ) {

        }
    }

}