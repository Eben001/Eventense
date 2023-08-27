package com.ebenezer.gana.eventense.presentation.screen.dashboard.profile

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ebenezer.gana.eventense.ui.theme.topAppBarBackground
import com.ebenezer.gana.eventense.ui.theme.topAppBarContentColor

@Composable
fun ProfileTopBar(onSave:() -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Profile", color = MaterialTheme.colors.topAppBarContentColor)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackground,
        actions = {
            ProfileTopBarActions(onSave = onSave)

        }
    )
}
@Composable
fun ProfileTopBarActions(
    onSave: () -> Unit
) {
    SaveAction(onSave = onSave)
}
@Composable
fun SaveAction(
    onSave: () -> Unit,
) {
    IconButton(onClick = onSave) {
        Icon(
            imageVector = Icons.Default.Save, contentDescription =
            "save icon", tint = MaterialTheme.colors.topAppBarContentColor
        )

    }

}



@Preview
@Composable
fun ProfileTopBarPreview() {
    ProfileTopBar(onSave = {})
}