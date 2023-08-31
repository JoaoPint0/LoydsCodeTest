package com.loyds.common.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.loyds.common.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.io.IOException

sealed class BaseUiState<out T> {

    object Loading : BaseUiState<Nothing>()

    data class Error(val throwable: Throwable?) : BaseUiState<Nothing>()

    data class Loaded<T>(
        val data: T
    ) : BaseUiState<T>()

    @Composable
    fun HandleUiState(
        modifier: Modifier,
        refreshClicked: () -> Unit,
        content: @Composable (Modifier, T) -> Unit
    ) {
        when (this) {
            is Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    ErrorState(this@BaseUiState.message(), refreshClicked)
                }
            }
            is Loaded -> content(modifier, this.data)
            Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    LoadingState()
                }
            }
        }
    }

    @Composable
    fun Error.message() = when (this.throwable) {
        is IOException -> stringResource(id = R.string.network_error)
        else -> stringResource(id = R.string.generic_error)
    }

    fun handleUiState(
        scope: LazyListScope,
        modifier: Modifier,
        refreshClicked: () -> Unit,
        content: LazyListScope.(Modifier, T) -> Unit
    ) {
        when (this@BaseUiState) {
            is Error -> scope.item {
                scope.item {
                    Box(modifier = Modifier.fillMaxSize()) {
                        ErrorState(this@BaseUiState.message(), refreshClicked)
                    }
                }
            }
            is Loaded -> scope.content(modifier, this@BaseUiState.data)
            Loading -> {
                scope.item {
                    Box(modifier = Modifier.fillMaxSize()) {
                        LoadingState()
                    }
                }
            }
        }
    }
}


fun <T, W> Flow<T>.asResult(mapper: (T) -> W): Flow<BaseUiState<W>> {
    return this
        .map<T, BaseUiState<W>> {
            BaseUiState.Loaded(mapper(it))
        }
        .onStart { emit(BaseUiState.Loading) }
        .catch { emit(BaseUiState.Error(it)) }
}