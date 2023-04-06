package com.rf.pod_test_04_6

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform