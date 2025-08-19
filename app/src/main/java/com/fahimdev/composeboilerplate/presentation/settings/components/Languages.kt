package com.fahimdev.composeboilerplate.presentation.settings.components

enum class Languages {
    ENGLISH,
    BANGLA,
    NORWEGIAN
}

fun getLanguages(): List<Languages> =
    listOf(
        Languages.ENGLISH,
        Languages.BANGLA,
        Languages.NORWEGIAN
    )

fun Languages.getLanguageName(): String {
    return when (this) {
        Languages.ENGLISH -> "English"
        Languages.BANGLA -> "বাংলা"
        Languages.NORWEGIAN -> "Norsk"
    }
}