package com.loyds.common.state

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.LoadingState() {
    CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
        strokeWidth = 1.dp
    )
}