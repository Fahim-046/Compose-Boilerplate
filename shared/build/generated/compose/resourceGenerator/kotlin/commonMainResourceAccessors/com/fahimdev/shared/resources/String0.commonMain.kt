@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package com.fahimdev.shared.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.StringResource

private object CommonMainString0 {
  public val about_description: StringResource by 
      lazy { init_about_description() }

  public val about_title: StringResource by 
      lazy { init_about_title() }

  public val app_name: StringResource by 
      lazy { init_app_name() }

  public val click_me: StringResource by 
      lazy { init_click_me() }

  public val hello_multiplatform: StringResource by 
      lazy { init_hello_multiplatform() }

  public val platform_info: StringResource by 
      lazy { init_platform_info() }

  public val welcome_message: StringResource by 
      lazy { init_welcome_message() }
}

@InternalResourceApi
internal fun _collectCommonMainString0Resources(map: MutableMap<String, StringResource>) {
  map.put("about_description", CommonMainString0.about_description)
  map.put("about_title", CommonMainString0.about_title)
  map.put("app_name", CommonMainString0.app_name)
  map.put("click_me", CommonMainString0.click_me)
  map.put("hello_multiplatform", CommonMainString0.hello_multiplatform)
  map.put("platform_info", CommonMainString0.platform_info)
  map.put("welcome_message", CommonMainString0.welcome_message)
}

internal val Res.string.about_description: StringResource
  get() = CommonMainString0.about_description

private fun init_about_description(): StringResource =
    org.jetbrains.compose.resources.StringResource(
  "string:about_description", "about_description",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/com.fahimdev.shared.resources/values/strings.commonMain.cvr", 10, 145),
    )
)

internal val Res.string.about_title: StringResource
  get() = CommonMainString0.about_title

private fun init_about_title(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:about_title", "about_title",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/com.fahimdev.shared.resources/values/strings.commonMain.cvr", 156, 27),
    )
)

internal val Res.string.app_name: StringResource
  get() = CommonMainString0.app_name

private fun init_app_name(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:app_name", "app_name",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/com.fahimdev.shared.resources/values/strings.commonMain.cvr", 184, 44),
    )
)

internal val Res.string.click_me: StringResource
  get() = CommonMainString0.click_me

private fun init_click_me(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:click_me", "click_me",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/com.fahimdev.shared.resources/values/strings.commonMain.cvr", 229, 28),
    )
)

internal val Res.string.hello_multiplatform: StringResource
  get() = CommonMainString0.hello_multiplatform

private fun init_hello_multiplatform(): StringResource =
    org.jetbrains.compose.resources.StringResource(
  "string:hello_multiplatform", "hello_multiplatform",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/com.fahimdev.shared.resources/values/strings.commonMain.cvr", 258, 71),
    )
)

internal val Res.string.platform_info: StringResource
  get() = CommonMainString0.platform_info

private fun init_platform_info(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:platform_info", "platform_info",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/com.fahimdev.shared.resources/values/strings.commonMain.cvr", 330, 41),
    )
)

internal val Res.string.welcome_message: StringResource
  get() = CommonMainString0.welcome_message

private fun init_welcome_message(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:welcome_message", "welcome_message",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/com.fahimdev.shared.resources/values/strings.commonMain.cvr", 372, 67),
    )
)
