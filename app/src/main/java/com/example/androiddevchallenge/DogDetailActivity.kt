package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DogDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dogName = intent.getStringExtra("dog_name")
        val dogPic = intent.getStringExtra("dog_pic")
        val dogDes = intent.getStringExtra("dog_des")
        if (dogName == null || dogPic == null || dogDes == null) {
            return
        }
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = dogName)
                        },
                        navigationIcon = {
                            IconButton(onClick = { onBackPressed() }) {
                                val backIcon: Painter = painterResource(R.drawable.ic_back)
                                Icon(painter = backIcon, contentDescription = "ic_back")
                            }
                        }
                    )
                }
            ) {
                dogDetail(name = dogName, pic = dogPic, desc = dogDes)
            }
        }
    }

    @Composable
    fun dogDetail(name: String, pic: String, desc: String) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            DogAvatar(
                avatar = pic,
                name = name
            )
            Spacer(
                modifier = Modifier.requiredHeight(26.dp)
            )
            DogIntroduction(
                introduction = desc
            )
        }
    }

    @Composable
    fun DogAvatar(avatar: String, name: String) {
        val imageIdentity = App.context.resources.getIdentifier(
            avatar, "drawable",
            App.context.packageName
        )
        val image: Painter = painterResource(imageIdentity)
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = image,
                contentDescription = name,
                modifier = Modifier
                    .requiredSize(150.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }

    @Composable
    fun DogIntroduction(introduction: String) {
        Text(
            text = introduction,
            fontSize = 18.sp,
            style = MaterialTheme.typography.body1
        )
    }
}