package com.group.custombottomnavigationbar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform