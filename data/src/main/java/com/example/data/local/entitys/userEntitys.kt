package com.example.data.local.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalUserModel(
    @PrimaryKey
    @ColumnInfo("uuid") var uuid: String = "",
    @ColumnInfo("title") var title: String? = null,
    @ColumnInfo("firstName") var firstName: String? = null,
    @ColumnInfo("lastName") var lastName: String? = null,
    @ColumnInfo("email") var email: String = "",
    @ColumnInfo("phone") var phone: String = "",
    @ColumnInfo("gender") var gender: String = "",
    @ColumnInfo("location") var location: LocalUserLocationModel? = null,
    @ColumnInfo("picture") var picture: LocalUserPictureModel? = null,
    @ColumnInfo("pageNumber") var pageNumber: Int = 0,
    @ColumnInfo("coordination") var coordination: LocalCoordinationModel? = null,

    )

fun localUserModel(block: LocalUserModel.() -> Unit): LocalUserModel = LocalUserModel().apply(block)


/**
 * User location
 */
data class LocalUserLocationModel(
    var street: String = "",
    var city: String = "",
    var state: String = "",
    var country: String = "",
    var postcode: String = "",
)

/**
 * DSL location
 */
fun LocalUserModel.location(block: LocalUserLocationModel.() -> Unit) {
    location = LocalUserLocationModel().apply(block)
}


/**
 * User  Coordination location
 */
data class LocalCoordinationModel(
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
)

/**
 * DSL Coordination location
 */

fun LocalUserModel.coordination(block: LocalCoordinationModel.() -> Unit) {
    coordination = LocalCoordinationModel().apply(block)
}
/**
 *  User Picture
 */
data class LocalUserPictureModel(
    var large: String = "",
    var medium: String = "",
    var thumbnail: String = ""
)

/**
 *  User Picture DSL
 */

fun LocalUserModel.picture(block: LocalUserPictureModel.() -> Unit) {
    picture = LocalUserPictureModel().apply(block)
}