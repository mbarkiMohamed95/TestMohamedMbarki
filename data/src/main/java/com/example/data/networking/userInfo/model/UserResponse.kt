
package com.example.data.networking.userInfo.model
import com.google.gson.annotations.SerializedName


data class UserResponse(

	@SerializedName("results") val results : List<Results>,
	@SerializedName("info") val info : Info
)