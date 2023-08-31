package com.loyds.common.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.loyds.common.R

@Composable
fun BoxScope.EmptyState(
    entity: String,
) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.empty_state_title, entity), style = MaterialTheme.typography.titleMedium)
        Text(text = stringResource(id = R.string.empty_state_subtitle, entity), style = MaterialTheme.typography.bodyMedium)
    }
}