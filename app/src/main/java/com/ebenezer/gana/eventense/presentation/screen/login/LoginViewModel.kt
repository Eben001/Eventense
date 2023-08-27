package com.ebenezer.gana.eventense.presentation.screen.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebenezer.gana.eventense.domain.model.ApiRequest
import com.ebenezer.gana.eventense.domain.model.ApiResponse
import com.ebenezer.gana.eventense.domain.model.MessageBarState
import com.ebenezer.gana.eventense.domain.repository.Repository
import com.ebenezer.gana.eventense.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "LoginViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _signedInState: MutableState<Boolean> = mutableStateOf(false)
    val signedInState: State<Boolean> = _signedInState

    private val _isLoggedIn: MutableState<Boolean> = mutableStateOf(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn

    private val _messageBarState: MutableState<MessageBarState> = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState



    private val _apiResponse: MutableState<RequestState<ApiResponse>> =
        mutableStateOf(RequestState.Idle)

    val apiResponse: State<RequestState<ApiResponse>> = _apiResponse

    init {
        viewModelScope.launch {
            repository.readSignedInState().collect { completed ->
                _signedInState.value = completed
            }
        }
    }

    fun saveSignedInState(signedIn: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveSignedInState(signedIn = signedIn)
        }
    }

    fun updateMessageBarState() {
        _messageBarState.value = MessageBarState(error = GoogleAccountNotFoundException())
    }

    fun verifyTokenOnBackend(request: ApiRequest) {
        _apiResponse.value = RequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = repository.verifyTokenOnBackend(request)
                _apiResponse.value = RequestState.Success(response)
                _isLoggedIn.value = true
                Log.d(TAG, "verifyTokenOnBackend: apiResponse: ${_apiResponse.value}")
                _messageBarState.value = MessageBarState(
                    message = response.message,
                    error = response.error
                )
            }
        } catch (e: Exception) {
            Log.d(TAG, "verifyTokenOnBackend: error ${e.message}")
            _isLoggedIn.value = false
            _apiResponse.value = RequestState.Error(e)
            _messageBarState.value = MessageBarState(
                error = e
            )
        }
    }


}

class GoogleAccountNotFoundException(
    override val message: String? =
        "Google Account not found"
) : Exception()