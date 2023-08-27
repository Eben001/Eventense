package com.ebenezer.gana.eventense.presentation.screen.dashboard.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ebenezer.gana.eventense.R
import com.ebenezer.gana.eventense.component.GoogleButton
import com.ebenezer.gana.eventense.component.MessageBar
import com.ebenezer.gana.eventense.domain.model.ApiResponse
import com.ebenezer.gana.eventense.domain.model.MessageBarState
import com.ebenezer.gana.eventense.ui.theme.Purple40
import com.ebenezer.gana.eventense.util.RequestState

@Composable
fun ProfileContent(
    apiResponse: RequestState<ApiResponse>?,
    messageBarState: MessageBarState?,
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    emailAddress: String?,
    profilePhoto: String?,
    onSignOutClicked: () -> Unit
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(modifier = Modifier.weight(1f)) {
            if (apiResponse is RequestState.Loading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(), color = Purple40
                )
            } else {
                if (messageBarState != null) {
                    MessageBar(messageBarState = messageBarState)
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(9f)
                .fillMaxWidth(0.7f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CentralContent(
                firstName = firstName,
                onFirstNameChanged = onFirstNameChanged,
                lastName = lastName,
                onLastNameChanged = onLastNameChanged,
                emailAddress = emailAddress,
                profilePhoto = profilePhoto
            ) {
                onSignOutClicked()
            }

        }


    }

}


@Composable
fun CentralContent(
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    emailAddress: String?,
    profilePhoto: String?,
    onSignOutClicked: () -> Unit
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(profilePhoto).crossfade(true)
            .placeholder(R.drawable.ic_placeholder).build()
    )
    Image(
        painter = painter, contentDescription = "profile photo",
        modifier = Modifier
            .padding(bottom = 40.dp)
            .size(150.dp)
            .clip(CircleShape),
    )


    OutlinedTextField(
        value = firstName,
        onValueChange = { onFirstNameChanged(it) },
        label = { Text(text = "First Name") },
        textStyle = MaterialTheme.typography.body1,
        singleLine = true
    )

    OutlinedTextField(
        value = lastName,
        onValueChange = { onLastNameChanged(it) },
        label = { Text(text = "Last Name") },
        textStyle = MaterialTheme.typography.body1,
        singleLine = true
    )

    OutlinedTextField(
        value = emailAddress.toString(),
        onValueChange = { },
        label = { Text(text = "Email Name") },
        textStyle = MaterialTheme.typography.body1,
        singleLine = true,
        enabled = false
    )

    GoogleButton(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 24.dp),
        primaryText = "Sign Out",
        secondaryText = "Sign Out",
        onClick = { onSignOutClicked() })
}


@Preview
@Composable
fun ProfileContentPreview() {
    ProfileContent(
        apiResponse = null,
        messageBarState = null,
        firstName = "Eben",
        onFirstNameChanged = {},
        lastName = "Gana",
        onLastNameChanged = {},
        emailAddress = "ebenezerganan@gmail.com",
        profilePhoto = null
    ) {

    }
}

