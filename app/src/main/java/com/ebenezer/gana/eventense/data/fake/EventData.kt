package com.ebenezer.gana.eventense.data.fake

import com.ebenezer.gana.eventense.R
import com.ebenezer.gana.eventense.domain.model.Event
import com.ebenezer.gana.eventense.domain.model.User
import java.util.UUID

object EventData {

    private val chidimma = User(
        id = UUID.randomUUID().toString(),
        name = "Chidimma",
        emailAddress = "chidimma@mail.com",
        profilePhotoRes = R.drawable.chidimma,
    )
    private val eben = User(
        id = UUID.randomUUID().toString(),
        name = "Eben",
        emailAddress = "bob@mail.com",
        profilePhotoRes = R.drawable.eben,
    )
    private val charlie = User(
        id = UUID.randomUUID().toString(),
        name = "Charlie",
        emailAddress = "charlie@mail.com",
        profilePhotoRes = R.drawable.chalie,
    )
    private val david = User(
        id = UUID.randomUUID().toString(),
        name = "David",
        emailAddress = "david@mail.com",
        profilePhotoRes = R.drawable.david,
    )
    private val bisola = User(
        id = UUID.randomUUID().toString(),
        name = "Bisola",
        emailAddress = "bisola@mail.com",
        profilePhotoRes = R.drawable.bisola,
    )
    private val frank = User(
        id = UUID.randomUUID().toString(),
        name = "Frank",
        emailAddress = "frank@mail.com",
        profilePhotoRes = R.drawable.frank,
    )
    private val grace = User(
        id = UUID.randomUUID().toString(),
        name = "Grace",
        emailAddress = "grace@mail.com",
        profilePhotoRes = R.drawable.grace,
    )
    private val hannah = User(
        id = UUID.randomUUID().toString(),
        name = "Hannah",
        emailAddress = "hannah@mail.com",
        profilePhotoRes = R.drawable.hannah,
    )

    private val precious = User(
        id = UUID.randomUUID().toString(),
        name = "Precious",
        emailAddress = "precious@mail.com",
        profilePhotoRes = R.drawable.precious,
    )


    val eventList = listOf(
        Event(
            id = UUID.randomUUID().toString(),
            title = "Igbo Cultural Day",
            details = "Join us to celebrate our rich Igbo heritage on our annual cultural day. Experience traditional dances, music, and delicious Igbo cuisine.",
            attachedImages = listOf(R.drawable.igbo_cultural_1, R.drawable.igbo_cultural_2, R.drawable.igbo_cultural_3),
            hasBeenLiked = true,
            likes = 180,
            comments = 40,
            user = chidimma


        ),
        Event(
            id = UUID.randomUUID().toString(),
            title = "Tech Conference",
            details = "Stay updated with the latest tech trends at our annual tech conference.",
            attachedImages = listOf(R.drawable.coding_bootcamp1, R.drawable.coding_bootcamp2, R.drawable.coding_bootcamp3),
            hasBeenLiked = true,
            likes = 150,
            comments = 30,
            user= eben
        ), Event(
            id = UUID.randomUUID().toString(),
            title = "Art Gallery Opening",
            details = "Explore stunning artworks at our gallery opening on Friday.",
            attachedImages = listOf(R.drawable.art_gallery1, R.drawable.art_gallery2),
            hasBeenLiked = false,
            likes = 32,
            comments = 8, user = charlie
        ), Event(
            id = UUID.randomUUID().toString(),
            title = "Fitness Challenge",
            details = "Ready for a fitness challenge? Join us on Sunday morning for a fun workout.",
            attachedImages = listOf(R.drawable.fitness_challenge),
            hasBeenLiked = true,
            likes = 75,
            comments = 12, user = david
        ),

        Event(
            id = UUID.randomUUID().toString(),
            title = "Cooking Workshop",
            details = "Learn the art of cooking in our hands-on workshop. Limited seats available!",
            attachedImages = listOf(R.drawable.cooking_challenge, R.drawable.cooking_challenge2),
            hasBeenLiked = true,
            likes = 120,
            comments = 20,
            user = bisola
        ), Event(
            id = UUID.randomUUID().toString(),
            title = "Charity Run",
            details = "Run for a cause! Join our charity run to support local shelters.",
            attachedImages = listOf(R.drawable.charity_run),
            hasBeenLiked = true,
            likes = 250,
            comments = 45, user = frank
        ), Event(
            id = UUID.randomUUID().toString(),
            title = "Live Music Night",
            details = "Enjoy a night of live music and delicious food with friends.",
            attachedImages = listOf(R.drawable.music_night, R.drawable.music_night2),
            hasBeenLiked = false,
            likes = 50,
            comments = 5, user = precious
        ), Event(
            id = UUID.randomUUID().toString(),
            title = "Yoga Retreat",
            details = "Escape the hustle and bustle with a rejuvenating weekend yoga retreat.",
            attachedImages = listOf(R.drawable.yoga_practice),
            hasBeenLiked = true,
            likes = 180,
            comments = 25, user = grace
        )
    )


}