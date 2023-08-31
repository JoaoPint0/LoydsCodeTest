package com.loyds.common.state

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<T> : ViewModel() {

    private val message = MutableStateFlow<(@StringRes Int)?>(null)

    val state: StateFlow<BaseUiState<T>>
        get() = _state

    protected var _state = MutableStateFlow<BaseUiState<T>>(BaseUiState.Loading)

    protected fun sendMessage(new: Int) {
        message.update { new }
    }

    private fun clearMessage() {
        message.update { null }
    }

    abstract fun onPageRefresh()

    protected fun <W> Result<W>.onResult(mapper: (W) -> T){
        this.onSuccess { result ->
            _state.update {  BaseUiState.Loaded(mapper(result)) }
        }.onFailure { error ->
            _state.update {  BaseUiState.Error(error) }
        }
    }

    protected fun onLoaded(transform :  (T) -> T){
        _state.update { if(it is BaseUiState.Loaded) it.copy(data = transform(it.data)) else it }
    }

    @Composable
    fun HandleUiState(
        topBar : @Composable () -> Unit = {},
        floatingActionButton: @Composable () -> Unit = {},
        floatingActionButtonPosition: FabPosition = FabPosition.End,
        content: @Composable (Modifier, T) -> Unit,
    ) {
        val context = LocalContext.current
        val state by state.collectAsStateWithLifecycle()
        val message: (@StringRes Int)? by message.collectAsStateWithLifecycle()

        message?.let {
            Toast.makeText(context, stringResource(id = it), Toast.LENGTH_SHORT).show()
            clearMessage()
        }

        Scaffold(
            topBar = topBar,
            floatingActionButton = floatingActionButton,
            floatingActionButtonPosition = floatingActionButtonPosition
        ) {
            state.HandleUiState(
                modifier = Modifier.padding(it),
                refreshClicked = this::onPageRefresh,
                content = content
            )
        }
    }
}