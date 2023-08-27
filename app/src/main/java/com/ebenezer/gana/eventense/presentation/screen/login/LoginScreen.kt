package com.ebenezer.gana.eventense.presentation.screen.login

import android.app.Activity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ebenezer.gana.eventense.domain.model.ApiRequest
import com.ebenezer.gana.eventense.domain.model.ApiResponse
import com.ebenezer.gana.eventense.navigation.Screen
import com.ebenezer.gana.eventense.presentation.screen.common.StartActivityForResult
import com.ebenezer.gana.eventense.presentation.screen.common.signIn
import com.ebenezer.gana.eventense.util.RequestState

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigationAction:() -> Unit) {

    val signedInState by loginViewModel.signedInState
    val messageBarState by loginViewModel.messageBarState
    val apiResponse by loginViewModel.apiResponse

    var isButtonLoading by remember {
        mutableStateOf(false)
    }

    // If already signed in, navigate to the dashboard immediately
    LaunchedEffect(key1 = signedInState) {
        if (signedInState) {
            navigateToDashboard(navHostController)
            navigationAction()
        }
    }

    Scaffold(topBar = { LoginTopBar() }) {
        it.toString()
        LoginContent(isButtonLoading = isButtonLoading, messageBarState = messageBarState,
            onButtonClicked = {
                isButtonLoading = true
            })

    }

    val activity = LocalContext.current as Activity

    StartActivityForResult(
        key = isButtonLoading,
        onResultReceived = { tokenId ->
            loginViewModel.verifyTokenOnBackend(request = ApiRequest(tokenId = tokenId))
        },
        onDialogDismissed = {
            isButtonLoading = false
            loginViewModel.saveSignedInState(signedIn = false) },
        launcher = { activityLauncher ->
            if(isButtonLoading){
                signIn(activity = activity,
                    launchActivityResult = { intentSenderRequest ->
                        activityLauncher.launch(intentSenderRequest)
                    },
                    accountNotFound = {
                        isButtonLoading = false
                        loginViewModel.saveSignedInState(signedIn = false)
                        loginViewModel.updateMessageBarState()
                    })
            }

        }
    )

    LaunchedEffect(key1 = apiResponse){
        when(apiResponse){
            is RequestState.Success -> {
                val response = (apiResponse as RequestState.Success<ApiResponse>).data.success
                if(response){
                    loginViewModel.saveSignedInState(signedIn = true)
                    navigateToDashboard(navHostController)
                    navigationAction()
                }else{
                    isButtonLoading = false
                    loginViewModel.saveSignedInState(false)
                }
            }
            else -> {

            }
        }
    }
}

private fun navigateToDashboard(navController:NavHostController) {
    navController.navigate(route = Screen.Event.route){
        popUpTo(route = Screen.Login.route){
            inclusive = true
        }
    }
}


