package com.example.musicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.components.AlbumCard
import com.example.musicapp.components.CurrPlayCard
import com.example.musicapp.components.RecentCard
import com.example.musicapp.components.WelcomeCard
import com.example.musicapp.models.Album
import com.example.musicapp.services.AlbumService
import com.example.musicapp.ui.theme.BackgroundColor
import com.example.musicapp.ui.theme.MusicAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.internal.http2.Header
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.log

@Composable

fun HomeScreen(
    //navController: NavController
) {

    var albums by remember {
        mutableStateOf(listOf<Album>())
    }

    var loading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(true) {
        try {
            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://music.juanfrausto.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(AlbumService::class.java)
            val result = async(Dispatchers.IO) {
                service.getAllAlbums()
            }
            Log.i("HomeScreen", "${result.await()}")
            albums = result.await()
            loading = false
        } catch (e: Exception) {
            loading = false
            Log.e("HomeScreen", e.toString())
        }
    }
    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        // Aquí iría la implementación de la pantalla de inicio con la lista de álbumes
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(BackgroundColor)
            ) {
                WelcomeCard()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "Albums",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                    )
                    TextButton(
                        onClick = {}
                    ) {
                        Text(
                            text = "See more",
                            fontSize = 14.sp,
                            color = Color.Blue
                        )
                    }

                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),

                    ) {
                    items(albums) { album ->
                        AlbumCard(album = album, onClick = {})
                    }

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "Recently Played",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                    )
                    TextButton(
                        onClick = {}
                    ) {
                        Text(
                            text = "See more",
                            fontSize = 14.sp,
                            color = Color.Blue
                        )
                    }

                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    items(albums) { album ->
                        RecentCard(album = album)

                    }
                }


            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                CurrPlayCard(album = albums.first())
            }


        }
    }
}



@Preview
@Composable
fun HomeScreenPreview(){
    MusicAppTheme {
        HomeScreen(/*rememberNavController()*/)
    }
}