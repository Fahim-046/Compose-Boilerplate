package com.fahimdev.shared.utils

expect class PlatformContext

expect fun getPlatformContext(): PlatformContext

// Platform-specific capabilities
expect class PlatformCapabilities {
    fun canMakePhoneCalls(): Boolean
    fun canSendSMS(): Boolean
    fun canAccessContacts(): Boolean
    fun canUseCamera(): Boolean
    fun canAccessLocation(): Boolean
    fun supportsHapticFeedback(): Boolean
    fun supportsFilePicker(): Boolean
}

expect fun getPlatformCapabilities(): PlatformCapabilities