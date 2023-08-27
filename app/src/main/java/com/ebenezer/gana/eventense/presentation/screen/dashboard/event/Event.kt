package com.ebenezer.gana.eventense.presentation.screen.dashboard.event

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ebenezer.gana.eventense.R
import com.ebenezer.gana.eventense.domain.model.Event
import com.ebenezer.gana.eventense.domain.model.User


@Composable
fun EventItem(event: Event) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        event.user.profilePhotoRes?.let {
            ProfileItem(
                modifier = Modifier.padding(bottom = 16.dp),
                userName = event.user.name,
                profilePhoto = it
            )
        }
        EventBody(event)
    }

}

@Composable
fun ProfileItem(modifier: Modifier, userName: String, profilePhoto: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = profilePhoto),
            contentScale = ContentScale.Crop,
            contentDescription = "Profile Image",
            modifier = modifier
                .size(48.dp)
                .shadow(8.dp, CircleShape)
        )

        Text(
            text = userName,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold
        )

    }

}

@Composable
fun EventBody(event: Event) {
    Card(
        shape = RoundedCornerShape(4.dp), elevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = event.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = event.details, fontSize = 20.sp)
                ImagePager(event.attachedImages)
                CommentBar(event)

            }
        }

    }

}
/*

@Preview
@Composable
fun EventCardPreview() {
    EventItem(User(id = "", name = "Eben", profilePhoto = R.drawable.levi, emailAddress = ""),
        event = Event(id = "", title = "The WiseOnes Tech Meet up", details = """
            Hey Guys, I'm inviting you all to our upcoming events coming soon...
        """.trimIndent(), attachedImages = listOf(R.drawable.guinea1, R.drawable.guinea2, R.drawable.guinea3), hasBeenLiked = true, likes = 12, comments = 3)
    )
}*/
