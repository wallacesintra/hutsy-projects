package com.example.weather.data.location

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.weather.data.location.models.LocationModel
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LocationDataSource(
    private val context: Context,
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val currentLocationRequest: com.google.android.gms.location.LocationRequest
): LocationSource {

    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override suspend fun fetchCurrentLocation(): Flow<LocationModel> {
        return callbackFlow {
            val callback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult){
                    try {
                        trySend(
                            LocationModel(
                                latitude = result.lastLocation?.latitude ?: 0.0,
                                longitude = result.lastLocation?.longitude ?: 0.0,
                            )
                        )
                    } catch (e: Exception){
                        Log.e("Location error", e.message.toString())
                    }
                }
            }

            if (ActivityCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )

            }else {

                fusedLocationProviderClient.requestLocationUpdates(
                    currentLocationRequest,
                    callback,
                    Looper.getMainLooper()
                )
                    .addOnFailureListener { e ->
                        close(e)
                    }

                awaitClose {
                    fusedLocationProviderClient.removeLocationUpdates(callback)
                }
            }
        }

    }
}