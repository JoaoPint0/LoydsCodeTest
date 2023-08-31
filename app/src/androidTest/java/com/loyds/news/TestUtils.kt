package com.loyds.news

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.loyds.designsystem.theme.NewsTheme

fun ComposeContentTestRule.setContentWithTheme(content: @Composable () -> Unit) {
    setContent {
       NewsTheme(false, false, content)
    }
}