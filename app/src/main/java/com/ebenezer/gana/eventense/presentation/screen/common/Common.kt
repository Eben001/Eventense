package com.ebenezer.gana.eventense.presentation.screen.common

import android.app.Activity
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.ebenezer.gana.eventense.util.Constants.CLIENT_ID
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes

@Composable
fun StartActivityForResult(
    key: Any,
    onResultReceived: (String) -> Unit,
    onDialogDismissed: () -> Unit,
    launcher: (
        ManagedActivityResultLauncher<IntentSenderRequest,
                ActivityResult>
    ) -> Unit
) {

    val activity = LocalContext.current as Activity
    val activityLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult(),
            onResult = { result ->
                try {
                    if (result.resultCode == Activity.RESULT_OK) {
                        val oneTapClient = Identity.getSignInClient(activity)
                        val credentials = oneTapClient.getSignInCredentialFromIntent(
                            result.data
                        )
                        val tokenId = credentials.googleIdToken

                        if (tokenId != null) {
                            Log.d("Common", "StartActivityForResult: $tokenId")
                            onResultReceived(tokenId)
                        }
                    } else {
                        Log.d("StartActivityForResult", "BLACK SCRIM CLICKED, DIALOG CLOSED")
                        onDialogDismissed()
                    }
                } catch (e: ApiException) {
                    when (e.statusCode) {
                        CommonStatusCodes.CANCELED -> {
                            Log.d(
                                "StartActivityForResult",
                                "ONE  TAP DIALOG CANCELLED"
                            )
                            onDialogDismissed()
                        }
                        CommonStatusCodes.NETWORK_ERROR -> {
                            Log.d(
                                "StartActivityForResult",
                                "One Tap Network Error"
                            )
                            onDialogDismissed()
                        }
                        else -> {
                            Log.d(
                                "StartActivityForResult",
                                "${e.message}"
                            )
                            onDialogDismissed()
                        }

                    }
                }
            })


    LaunchedEffect(key1 = key) {
        launcher(activityLauncher)
    }

}


//with signIn, we are showing those authorized accounts and not all
fun signIn(
    activity: Activity,
    launchActivityResult: (IntentSenderRequest) -> Unit,
    accountNotFound: () -> Unit
) {
    val oneTapClient = Identity.getSignInClient(
        activity
    )
    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(CLIENT_ID)
                .setFilterByAuthorizedAccounts(true)
                .build()
        )
        .build()

    oneTapClient.beginSignIn(signInRequest)
        .addOnSuccessListener { result ->
            try {
                launchActivityResult(
                    IntentSenderRequest.Builder(
                        result.pendingIntent.intentSender
                    ).build()
                )
            } catch (e: Exception) {
                Log.e("Sign in", "Couldn't start one tap sign in ${e.message}")
            }
        }
        .addOnFailureListener {
            Log.d("SingIn", "signing up...")
            signUp(
                activity = activity,
                launchActivityResult = launchActivityResult,
                accountNotFound = accountNotFound
            )
        }
}


//Not actually creating an account with google instead,
//we're showing all the google devices we have on the device
fun signUp(
    activity: Activity,
    launchActivityResult: (IntentSenderRequest) -> Unit,
    accountNotFound: () -> Unit
) {
    val oneTapClient = Identity.getSignInClient(
        activity
    )
    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(CLIENT_ID)
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()

    oneTapClient.beginSignIn(signInRequest)
        .addOnSuccessListener { result ->
            try {
                launchActivityResult(
                    IntentSenderRequest.Builder(
                        result.pendingIntent.intentSender
                    ).build()
                )
            } catch (e: Exception) {
                Log.e("SignUp", "Couldn't start one tap UI ${e.message}")
            }
        }
        .addOnFailureListener {
            Log.e("SingUp", "${it.message}")
            accountNotFound()
        }

}