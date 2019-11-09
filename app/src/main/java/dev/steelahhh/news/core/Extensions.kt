package dev.steelahhh.news.core

import android.os.Build

// Check that we're in a unit test
fun isUnitTest(): Boolean {
    return Build.BRAND == null || Build.BRAND.startsWith(Build.UNKNOWN)
}
