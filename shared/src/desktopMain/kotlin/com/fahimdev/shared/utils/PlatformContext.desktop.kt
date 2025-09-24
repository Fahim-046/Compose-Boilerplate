package com.fahimdev.shared.utils

actual class PlatformContext

actual fun getPlatformContext(): PlatformContext = PlatformContext()

actual class PlatformCapabilities {
    actual fun canMakePhoneCalls(): Boolean = false
    actual fun canSendSMS(): Boolean = false
    actual fun canAccessContacts(): Boolean = false
    actual fun canUseCamera(): Boolean = true // Some desktop computers have cameras
    actual fun canAccessLocation(): Boolean = false
    actual fun supportsHapticFeedback(): Boolean = false
    actual fun supportsFilePicker(): Boolean = true
}

actual fun getPlatformCapabilities(): PlatformCapabilities = PlatformCapabilities()