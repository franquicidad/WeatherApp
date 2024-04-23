package com.example.weatherglobantapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherglobantapp.Util.UserLocation
import com.example.weatherglobantapp.data.converters.Converters
import com.example.weatherglobantapp.dataModel.landing.Weather
import com.example.weatherglobantapp.dataModel.landing.forecast.Forecast
import com.example.weatherglobantapp.databinding.FragmentHomeBinding
import com.example.weatherglobantapp.ui.adapters.WeatherListAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
//    private lateinit var deferred: LatitudeLongitude

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (container != null) {
            val userLocation = UserLocation()
            userLocation.getUserLocationClassMethod(container, this) { lat, long ->
                homeViewModel.getWeatherList(lat, long)
            }
        }
        homeViewModel.weather.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                homeViewModel.forecast.collect { forecast ->
                    updateUI(forecast, binding, it)
                    println("-----------------------------> $it")

                    println("-----------------------------> $forecast")

                    binding.weatherDetailRecyclerView.apply {
                        adapter = it?.let { it1 ->
                            forecast?.let { it2 ->
                                WeatherListAdapter(
                                    list = it.weather,
                                    forecast = it2
                                )
                            }
                        }
                        layoutManager = LinearLayoutManager(context)
                    }
                }
            }
        }

        return root
    }

    private fun updateUI(forecast: Forecast?, binding: FragmentHomeBinding, weather: Weather?) {
        val converter = Converters()
        binding.cityName.text = forecast?.city?.name
        binding.windSpeed.text = weather?.wind?.speed?.toString()
        binding.feelsLike.text = "Feels like:${
            weather?.main?.feels_like?.let {
                converter.convertDegreeToFarenheigh(
                    it
                )
            }.also { 
                it.toString().substring(0,4)
            }
        }"
        binding.farenheigDivision.text = "${
            weather?.main?.temp_max?.let {
                converter.convertDegreeToFarenheigh(
                    it
                )
            }.also { it.toString().substring(0,4) }
        }/${
            weather?.main?.temp_min?.let {
                converter.convertDegreeToFarenheigh(
                    it
                )
            }.also { it.toString().substring(0,4) }
        }"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@SuppressLint("MissingPermission")
fun getCurrentLocationMethod(
    fusedLocationClient: FusedLocationProviderClient,
    onSuccess: (lat: String, lot: String) -> Unit
) {
    val priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    val cancellationTokenSource = CancellationTokenSource()

    fusedLocationClient.getCurrentLocation(priority, cancellationTokenSource.token)
        .addOnSuccessListener { location ->
            Log.d("Location", "location is found: $location")
            onSuccess(location.latitude.toString(), location.longitude.toString())
        }
        .addOnFailureListener { exception ->
            Log.d("Location", "Oops location failed with exception: $exception")
        }
}










