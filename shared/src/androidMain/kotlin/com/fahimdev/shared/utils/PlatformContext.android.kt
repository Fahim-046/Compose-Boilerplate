package com.fahimdev.shared.utils

import android.content.Context

actual class PlatformContext(val androidContext: Context)

private var applicationContext: Context? = null

fun initializePlatformContext(context: Context) {
    applicationContext = context.applicationContext
}

actual fun getPlatformContext(): PlatformContext {
    val ctx = applicationContext ?: throw IllegalStateException("Platform context not initialized")
    return PlatformContext(ctx)
}

actual class PlatformCapabilities {
    actual fun canMakePhoneCalls(): Boolean = true
    actual fun canSendSMS(): Boolean = true
    actual fun canAccessContacts(): Boolean = true
    actual fun canUseCamera(): Boolean = true
    actual fun canAccessLocation(): Boolean = true
    actual fun supportsHapticFeedback(): Boolean = true
    actual fun supportsFilePicker(): Boolean = true
}

actual fun getPlatformCapabilities(): PlatformCapabilities = PlatformCapabilities()