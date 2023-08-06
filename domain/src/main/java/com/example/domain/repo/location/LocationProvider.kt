package com.example.domain.repo.location

import android.app.Activity
import android.location.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface LocationProvider {
    fun getCurrentLocation(
        coroutineScope: CoroutineScope
    ): Flow<Location?>

    fun checkGpsStatus(): Boolean
    fun setActivity(activity: Activity)
    fun checkLocationPermissions(): Boolean
}