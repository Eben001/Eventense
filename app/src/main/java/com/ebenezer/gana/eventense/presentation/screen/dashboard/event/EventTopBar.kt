package com.ebenezer.gana.eventense.presentation.screen.dashboard.event

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ebenezer.gana.eventense.ui.theme.topAppBarBackground
import com.ebenezer.gana.eventense.ui.theme.topAppBarContentColor

@Composable
fun EventTopBar() {
    TopAppBar(
        title = {
            Text(text = "Events", color = MaterialTheme.colors.topAppBarContentColor)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackground
    )
}




@Preview
@Composable
fun ProfileTopBarPreview() {
    EventTopBar()
}