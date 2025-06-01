package com.jocmp.buoyapp.ui

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Login : Route()

    @Serializable
    data object Settings : Route()

//    @Serializable
//    data object Articles : Route()
}
