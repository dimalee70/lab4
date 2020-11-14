package kz.lab4.utils

import kz.lab4.BuildConfig


object BuildType {

    fun isRelease() : Boolean {
        return BuildConfig.BUILD_TYPE == "release"
    }

    fun isDebug() : Boolean {
        return BuildConfig.BUILD_TYPE == "debug"
    }

}