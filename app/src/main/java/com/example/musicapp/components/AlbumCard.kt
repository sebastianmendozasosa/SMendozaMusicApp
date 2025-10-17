package com.example.musicapp.components

import android.R.attr.tint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.musicapp.models.Album
import com.example.musicapp.ui.theme.MusicAppTheme

@Composable
fun AlbumCard(
    album: Album,
    onClick : () -> Unit
){

    Box(
        modifier = Modifier
            .width(200.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable{
                onClick()
            },
    ) {
        AsyncImage(
            model = album.image,
            contentDescription = album.title,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .align (Alignment.BottomStart)
                .fillMaxWidth()
                .background(Color(0xFF2E004F).copy(alpha = 0.8f))
                .clip(RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = album.title ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    maxLines = 1
                )
                Text(
                    text = album.artist ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                )
            }
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = Color.Black,
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.White, shape = CircleShape)
                    .padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun AlbumCardPreview(){
    val sampleAlbum = Album(
        title = "Sample Album",
        artist = "Sample Artist",
        description = "Sample description",
        image = "https://via.placeholder.com/150",
        id = "qewfasedfcsdvsa"
    )
    MusicAppTheme {
        AlbumCard(
            album = sampleAlbum,
            onClick = { }
        )
    }

}

