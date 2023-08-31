package com.loyds.settings.ui.settings
                 
import com.loyds.common.state.BaseUiState
import com.loyds.common.state.BaseViewModel
import com.loyds.settings.models.SettingsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : BaseViewModel<SettingsUiState>() {


    init {
        onPageRefresh()
        

    }

    override fun onPageRefresh() {

    }


}