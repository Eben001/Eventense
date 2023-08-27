package com.ebenezer.gana.eventense.presentation.screen.login
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ebenezer.gana.eventense.ui.theme.topAppBarBackground
import com.ebenezer.gana.eventense.ui.theme.topAppBarContentColor

@Composable
fun LoginTopBar() {
    TopAppBar(title = {
        Text(
            text = "Sign In",
            color = MaterialTheme.colors.topAppBarContentColor
        )
    }, backgroundColor = MaterialTheme.colors.topAppBarBackground)

}

@Preview
@Composable
fun LoginTopBarPreview() {
    LoginTopBar()
}