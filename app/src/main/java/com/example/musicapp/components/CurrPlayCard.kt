package com.example.musicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.musicapp.models.Album
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.Purple80


@Composable
fun CurrPlayCard(
    album: Album,
){
Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
    shape = RoundedCornerShape(24.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF2E004F).copy(alpha = 0.8f))
    ) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        AsyncImage(
            model = album.image,
            contentDescription = album.title,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = album.title,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = album.artist,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))

            }
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .background(Color.White, CircleShape)
                .size(40.dp)
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = "Play", tint = Color(0xFF2E004F))
        }
        }
    }
}


@Preview
@Composable
fun CurrPlayCardPreview(){
    val sampleAlbum = Album(
        title = "Sample Album",
        artist = "Sample Artist",
        description = "Sample description",
        image = "https://via.placeholder.com/150",
        id = "qewfasedfcsdvsa"
    )
    MusicAppTheme {
        CurrPlayCard(
            album = sampleAlbum,
        )
    }

}