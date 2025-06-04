package com.jocmp.buoy.common

val String.withTrailingSeparator: String
    get() {
        return if (endsWith("/")) {
            this
        } else {
            "$this/"
        }
    }
