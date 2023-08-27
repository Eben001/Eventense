package com.ebenezer.gana.eventense.presentation.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ebenezer.gana.eventense.R
import com.ebenezer.gana.eventense.component.GoogleButton
import com.ebenezer.gana.eventense.component.MessageBar
import com.ebenezer.gana.eventense.domain.model.MessageBarState
import com.ebenezer.gana.eventense.ui.theme.Purple


@Composable
fun LoginContent(
    isButtonLoading:Boolean,
    messageBarState: MessageBarState?,
    onButtonClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxHeight()) {
        Image(
            painter = painterResource(id = R.drawable.bigbg),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        if (messageBarState != null) {
            MessageBar(messageBarState = messageBarState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 32.dp, vertical = 20.dp
                )

                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "Welcome to Eventense, Where Events Come to Life",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Purple,

            )

            Spacer(modifier = Modifier.fillMaxHeight(0.2f))

            CentralContent(
                isButtonLoading = isButtonLoading,
                onButtonClicked = onButtonClicked
            )

        }
    }

}

@Composable
fun CentralContent(
    isButtonLoading: Boolean,
    onButtonClicked: () -> Unit
) {
    Column(modifier = Modifier
        .padding(top = 8.dp)
        .height(280.dp)
        .width(320.dp)) {
        Card(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.White.copy(0.1f),
                    shape = RoundedCornerShape(27.dp)
                )
                .clip(RoundedCornerShape(27.dp))
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(27.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.blur_card_bg),
                    contentDescription = "Card Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {

                    Text(
                        text = stringResource(id = R.string.sign_in_title),
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(12.dp)
                    )


                    Text(
                        text = stringResource(id = R.string.sign_in_subtitle),
                        modifier = Modifier
                            .padding(bottom = 40.dp, top = 0.dp),
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        textAlign = TextAlign.Center, color = Color.White
                    )

                    GoogleButton(
                        loadingState = isButtonLoading,
                        onClick = onButtonClicked
                    )
                }
            }
        }
    }



}


@Preview
@Composable
fun LoginContentPreview() {
    LoginContent(isButtonLoading = true, messageBarState = null, {})
}