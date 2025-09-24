package com.fahimdev.shared.utils

import platform.Foundation.NSObject

actual class PlatformContext : NSObject()

actual fun getPlatformContext(): PlatformContext = PlatformContext()

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