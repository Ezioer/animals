/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "animals")
                            },
                            backgroundColor = Color.Cyan
                        )
                    }
                ) {
                    var list = mutableListOf<Dog>()
                    list.add(Dog("dog1", "test", "this is dog1", 0))
                    list.add(Dog("dog2", "test", "this is dog2", 0))
                    list.add(Dog("dog3", "test", "this is dog3", 0))
                    list.add(Dog("dog4", "test", "this is dog4", 0))
                    list.add(Dog("dog5", "test", "this is dog5", 0))
                    list.add(Dog("dog6", "test", "this is dog6", 0))
                    list.add(Dog("dog7", "test", "this is dog7", 0))
                    list.add(Dog("dog8", "test", "this is dog8", 0))
                    list.add(Dog("dog9", "test", "this is dog9", 0))
                    dogsList(list) { position, dog ->
                        val intent = Intent(this@MainActivity, DogDetailActivity::class.java)
                        intent.putExtra("dog_name", dog.dog_name)
                        intent.putExtra("dog_pic", dog.dog_pic)
                        intent.putExtra("dog_des", dog.dog_des)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    @Composable
    fun dogItem(posi: Int, dog: Dog, onClick: (position: Int, dog: Dog) -> Unit) {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
                .requiredHeight(220.dp)
                .clickable { onClick(posi, dog) }
        ) {
            CardItem(name = dog.dog_name, avatar = dog.dog_pic)
        }
    }

    @Composable
    fun dogsList(list: MutableList<Dog>, onClick: (position: Int, dog: Dog) -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            LazyColumn(Modifier.fillMaxWidth()) {
                itemsIndexed(list) { position, dog ->
                    dogItem(position, dog, onClick)
                }
            }
        }
    }

    @Composable
    fun CardItem(name: String, avatar: String) {
        val imageIdentity = App.context.resources.getIdentifier(
            avatar, "drawable",
            App.context.packageName
        )
        val image: Painter = painterResource(imageIdentity)
        Image(
            image,
            name,
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier.fillMaxSize(),
            Arrangement.Bottom
        ) {
            Surface(
                color = Color.Cyan,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = name,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

