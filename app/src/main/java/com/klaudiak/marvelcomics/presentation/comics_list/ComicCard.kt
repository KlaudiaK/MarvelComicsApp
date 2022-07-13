package com.klaudiak.marvelcomics.presentation.comics_list

import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.klaudiak.marvelcomics.R
import com.klaudiak.marvelcomics.data.models.local.ComicItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicCard(
    navController: NavController,
    comic: ComicItem
) {

    var isLoading by remember { mutableStateOf(true) }
    val image: String? = comic.thumbnail?.replace("http://", "https://")
    val description: String =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(comic.description, Html.FROM_HTML_MODE_LEGACY).toString()
            Html.fromHtml(comic.description, Html.FROM_HTML_MODE_COMPACT).toString()
        } else {
            Html.fromHtml(comic.description).toString()
        }


    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(16.dp)
            .heightIn(max = 200.dp)
            .clickable { navController.navigate("comic_detail_screen/${comic.id}") },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start

        ) {
            if (isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(100.dp)
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = "thumbnail",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(200.dp)
                    .aspectRatio(ratio = 0.7f, matchHeightConstraintsFirst = true)
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.CenterVertically),
                onLoading = { isLoading = true },
                onSuccess = { isLoading = false },
                onError = { isLoading = false },
                fallback = painterResource(id = R.drawable.marvel_default)
            )


            Box(
                Modifier.fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = comic.title,
                        modifier = Modifier

                            .padding(start = 8.dp, top = 6.dp),
                        color = Color.Black,
                        fontWeight = FontWeight.W500,
                        fontSize = 21.sp
                    )

                    Text(
                        text = "witten by ${comic.author}",
                        modifier = Modifier
                            .padding(start = 8.dp, top = 6.dp),
                        color = Color.Gray,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp
                    )
                    Text(
                        text = description,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 6.dp, bottom = 6.dp),
                        color = Color.DarkGray,
                        fontWeight = FontWeight.W300,
                        fontSize = 12.sp,

                        )
                }
            }
        }
    }

}