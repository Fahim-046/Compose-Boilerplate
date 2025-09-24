package com.fahimdev.shared.utils

actual class PlatformContext

actual fun getPlatformContext(): PlatformContext = PlatformContext()

actual class PlatformCapabilities {
    actual fun canMakePhoneCalls(): Boolean = false
    actual fun canSendSMS(): Boolean = false
    actual fun canAccessContacts(): Boolean = false
    actual fun canUseCamera(): Boolean = true // Web browsers can access cameras
    actual fun canAccessLocation(): Boolean = true // Web browsers can access location
    actual fun supportsHapticFeedback(): Boolean = false
    actual fun supportsFilePicker(): Boolean = true // Web browsers support file picker
}

actual fun getPlatformCapabilities(): PlatformCapabilities = PlatformCapabilities()