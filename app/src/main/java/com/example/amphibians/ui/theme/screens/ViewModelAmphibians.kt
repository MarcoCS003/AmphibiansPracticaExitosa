package com.example.amphibians.ui.theme.screens


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansDataApplication
import com.example.amphibians.model.AmphibiansData
import com.example.amphibians.network.AmphiRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState{
    data class Success(val data: List<AmphibiansData>):AmphibiansUiState
    object Error: AmphibiansUiState
    object Loading:AmphibiansUiState
}

class AmphibiansViewModel(
    private val amphiDataRepository: AmphiRepository
) : ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibiansData()
    }

    fun getAmphibiansData() {
        viewModelScope.launch{
            amphibiansUiState = AmphibiansUiState.Loading
            amphibiansUiState = try {
                AmphibiansUiState.Success(amphiDataRepository.getDataAmphi())

            }catch (e:IOException){
                AmphibiansUiState.Error
            }catch (e:HttpException){
                AmphibiansUiState.Error
            }

        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application= (this[APPLICATION_KEY] as AmphibiansDataApplication)
                val amphiDataRepository  = application.container.amphibianDataRepository
                AmphibiansViewModel(amphiDataRepository = amphiDataRepository)
            }
        }
    }
}

