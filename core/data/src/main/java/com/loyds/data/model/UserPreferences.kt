package com.loyds.data.model

data class UserPreferences(
    val theme: Theme,
    val dynamicColor: Boolean
)

enum class Theme {
    DARK,
    LIGHT,
    SYSTEM
}
