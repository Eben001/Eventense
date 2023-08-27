package com.ebenezer.gana.eventense.presentation.screen.dashboard.event

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebenezer.gana.eventense.R
import com.ebenezer.gana.eventense.domain.model.Event

@Composable
fun CommentBar(event: Event) {
    val likeImage = if (event.hasBeenLiked) R.drawable.favorite else R.drawable.favorite_border

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 4.dp)
    ) {
        Image(
            painter = painterResource(id = likeImage),
            contentDescription = "Favorite",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    // viewModel.postLiked(post)
                }
        )
        Text(
            text = "${event.likes}",
            modifier = Modifier.padding(start = 4.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.comment),
            contentDescription = "Comment",
            modifier = Modifier
                .padding(start = 16.dp)
                .size(32.dp),
            colorFilter = ColorFilter.tint(Color.Black),
        )
        Text("${event.comments}", modifier = Modifier.padding(start = 4.dp))
    }

}

/*
@Preview
@Composable
fun CommentBarPreview() {
    CommentBar(
        Event(
            id = "",
            title = "",
            details = "",
            attachedImages = emptyList(),
            hasBeenLiked = true,
            likes = 12,
            comments = 3,
        )
    )
}
*/

