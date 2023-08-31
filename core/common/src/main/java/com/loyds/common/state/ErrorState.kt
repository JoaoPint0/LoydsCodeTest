package com.loyds.common.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.loyds.common.R

@Composable
fun BoxScope.ErrorState(
    text: String,
    onRefresh: () -> Unit
) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, style = MaterialTheme.typography.titleMedium)
        Text(text = stringResource(id = R.string.try_refresh), style = MaterialTheme.typography.bodyMedium)
        OutlinedButton(onClick = onRefresh) {
            Text(text = stringResource(id = R.string.refresh), style = MaterialTheme.typography.titleMedium)
        }
    }
}