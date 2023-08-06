package com.example.data.repository.location

import android.app.Activity
import android.location.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface LocationProvider {
    fun getCurrentLocation(
        scope: CoroutineScope
    ): Flow<Location>

    fun checkGpsStatus(): Boolean
    fun setActivity(activity: Activity)
    fun checkLocationPermissions(): Boolean
}