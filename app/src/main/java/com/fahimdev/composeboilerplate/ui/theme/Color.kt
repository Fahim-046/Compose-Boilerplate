package com.fahimdev.composeboilerplate.ui.theme

import androidx.compose.ui.graphics.Color

// Light Theme Colors
val LightPrimary = Color(0xFFE97D27)     // Warm orange/amber (oklch(0.45 0.25 25))
val LightOnPrimary = Color(0xFFFFFFFF)
val LightPrimaryContainer = Color(0xFFFFEBD8)
val LightOnPrimaryContainer = Color(0xFF341200)

val LightSecondary = Color(0xFFE6E6E6)   // Soft gray
val LightOnSecondary = Color(0xFF1A1A1A)
val LightSecondaryContainer = Color(0xFFF5F5F5)
val LightOnSecondaryContainer = Color(0xFF333333)

val LightTertiary = Color(0xFFB3B3B3)    // Neutral gray for tertiary
val LightOnTertiary = Color(0xFF1A1A1A)
val LightTertiaryContainer = Color(0xFFEAEAEA)
val LightOnTertiaryContainer = Color(0xFF2A2A2A)

val LightError = Color(0xFFD32F2F)
val LightErrorContainer = Color(0xFFFFEDEA)
val LightOnError = Color(0xFFFFFFFF)
val LightOnErrorContainer = Color(0xFF5A1A0B)

val LightBackground = Color(0xFFFAFAFA) // Near white (oklch(0.98 0 0))
val LightOnBackground = Color(0xFF262626) // Dark charcoal (oklch(0.15 0 0))
val LightSurface = Color(0xFFF2F2F2)    // Light gray card (oklch(0.95 0 0))
val LightOnSurface = Color(0xFF262626)

val LightSurfaceVariant = Color(0xFFE0E0E0) // Accent gray
val LightOnSurfaceVariant = Color(0xFF4A4A4A)
val LightOutline = Color(0xFFD9D9D9)        // Border gray
val LightOutlineVariant = Color(0xFFE5E5EA)

val LightSurfaceContainer = Color(0xFFFDFDFD)
val LightSurfaceContainerHigh = Color(0xFFF5F5F7)
val LightSurfaceContainerHighest = Color(0xFFEFEFF4)

// Dark Theme Colors
val DarkPrimary = Color(0xFFFF922E)     // Bright orange/amber (oklch(0.70 0.28 25))
val DarkOnPrimary = Color(0xFF1A1A1A)
val DarkPrimaryContainer = Color(0xFF3A1E00)
val DarkOnPrimaryContainer = Color(0xFFFFEBD8)

val DarkSecondary = Color(0xFF1F1F1F)   // Darker gray (oklch(0.12 0 0))
val DarkOnSecondary = Color(0xFFE0E0E0)
val DarkSecondaryContainer = Color(0xFF2A2A2A)
val DarkOnSecondaryContainer = Color(0xFFF5F5F5)

val DarkTertiary = Color(0xFF333333)    // Neutral tertiary
val DarkOnTertiary = Color(0xFFF5F5F5)
val DarkTertiaryContainer = Color(0xFF404040)
val DarkOnTertiaryContainer = Color(0xFFEAEAEA)

val DarkError = Color(0xFFF87171)
val DarkErrorContainer = Color(0xFF7F1D1D)
val DarkOnError = Color(0xFFFFFFFF)
val DarkOnErrorContainer = Color(0xFFFFEDEA)

val DarkBackground = Color(0xFF080808)  // Deep black (oklch(0.03 0 0))
val DarkOnBackground = Color(0xFFF9F9F9) // Near white (oklch(0.98 0 0))
val DarkSurface = Color(0xFF101010)     // Dark gray card (oklch(0.06 0 0))
val DarkOnSurface = Color(0xFFF9F9F9)

val DarkSurfaceVariant = Color(0xFF262626) // Dark accent gray
val DarkOnSurfaceVariant = Color(0xFFB0B0B0)
val DarkOutline = Color(0xFF333333)        // Dark border
val DarkOutlineVariant = Color(0xFF2A2A2A)

val DarkSurfaceContainer = Color(0xFF171717)
val DarkSurfaceContainerHigh = Color(0xFF1F1F1F)
val DarkSurfaceContainerHighest = Color(0xFF262626)

// Extended Colors - Professional & Clean
object ExtendedColors {
    // Success - Fresh but not too bright
    val Success = Color(0xFF10B981)
    val OnSuccess = Color(0xFFFFFFFF)
    val SuccessContainer = Color(0xFF065F46)
    val OnSuccessContainer = Color(0xFFD1FAE5)

    // Warning - Warm but readable
    val Warning = Color(0xFFF59E0B)
    val OnWarning = Color(0xFF1A1A1A)
    val WarningContainer = Color(0xFF92400E)
    val OnWarningContainer = Color(0xFFFEF3C7)

    // Info - Clean blue
    val Info = Color(0xFF3B82F6)
    val OnInfo = Color(0xFFFFFFFF)
    val InfoContainer = Color(0xFF1E40AF)
    val OnInfoContainer = Color(0xFFDBEAFE)

    // Neutral colors for better UI
    val Neutral50 = Color(0xFFFAFAFA)
    val Neutral100 = Color(0xFFF5F5F5)
    val Neutral200 = Color(0xFFE5E5E5)
    val Neutral300 = Color(0xFFD4D4D4)
    val Neutral400 = Color(0xFFA3A3A3)
    val Neutral500 = Color(0xFF737373)
    val Neutral600 = Color(0xFF525252)
    val Neutral700 = Color(0xFF404040)
    val Neutral800 = Color(0xFF262626)
    val Neutral900 = Color(0xFF171717)

    // Status colors that work in both themes
    val Online = Color(0xFF22C55E)
    val Away = Color(0xFFF59E0B)
    val Busy = Color(0xFFEF4444)
    val Offline = Color(0xFF6B7280)
}