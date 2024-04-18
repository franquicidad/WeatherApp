package com.example.weatherglobantapp.Util

import android.Manifest
import android.content.pm.PackageManager
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.weatherglobantapp.ui.home.getCurrentLocationMethod
import com.google.android.gms.location.LocationServices

class UserLocation() {

    fun getUserLocationClassMethod(
        container: ViewGroup,
        fragment: Fragment,
        onSuccess: (lat: String, long: String) -> Unit
    ) {
        val fusedLocationProviderClient = container?.let {
            LocationServices.getFusedLocationProviderClient(
                it.context
            )
        }
        if (ActivityCompat.checkSelfPermission(
                container.context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                container.context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val locationPermissionRequest =
                fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

                    when {
                        permissions.getOrDefault(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            false
                        ) -> {
                            // Precise location access granted.
                            fusedLocationProviderClient?.let {
                                com.example.weatherglobantapp.ui.home.getCurrentLocationMethod(
                                    it,
                                    onSuccess
                                )
                            }

                        }

                        permissions.getOrDefault(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            false
                        ) -> {
                            // Only approximate location access granted.
                            fusedLocationProviderClient?.let {
                                com.example.weatherglobantapp.ui.home.getCurrentLocationMethod(
                                    it,
                                    onSuccess
                                )
                            }

                        }

                        else -> {
                            // No location access granted.
                            // Dialog hacer
                        }
                    }
                }
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )

        } else {
            fusedLocationProviderClient?.let { getCurrentLocationMethod(it, onSuccess) }

        }

    }
}

