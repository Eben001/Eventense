package com.ebenezer.gana.eventense.presentation.screen.dashboard.event

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ebenezer.gana.eventense.data.fake.EventData

@Composable
fun EventScreen() {
    PopulateEvents()

}

@Composable
fun PopulateEvents() {
    Scaffold(topBar = {
        EventTopBar()
    }) {
        it.calculateTopPadding()
        Box {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                for (event in EventData.eventList) {
                    item { EventItem(event = event) }
                }

            }
        }
    }
}

@Preview
@Composable
fun EventScreenPreview() {
    PopulateEvents()
}

