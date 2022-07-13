package com.klaudiak.marvelcomics.presentation.comic_detail

import android.text.Html
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.klaudiak.marvelcomics.R
import com.klaudiak.marvelcomics.data.models.local.ComicItem

import kotlinx.coroutines.launch


@Composable
@ExperimentalMaterialApi
fun BottomSheet(comic: ComicItem) {
    rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    rememberCoroutineScope()
    val uriHandler = LocalUriHandler.current

    val columnScrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    val image: String? = comic.thumbnail?.replace("http://", "https://")

    var description: String? = comic.description
    description = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(comic.description, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(comic.description).toString()
    }
    Log.i("HTML", description)
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
        sheetContentColor = Color.Transparent,
        sheetPeekHeight = 300.dp,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(top = 8.dp)
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            ) {
                Button(
                    onClick = {
                        scope.launch { scaffoldState.bottomSheetState.collapse() }
                    },
                    modifier = Modifier.height(12.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                ) {}
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Transparent),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .verticalScroll(columnScrollState)
                        .background(Color.Transparent)
                        .padding(64.dp),
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = comic.title,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = comic.author,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = description,
                        fontWeight = FontWeight.W300,
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp
                    )
                }


            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(bottom = 40.dp)
                    .background(Color.White),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = {
                        scope.launch { scaffoldState.bottomSheetState.collapse() }
                        uriHandler.openUri(comic.uriDetails)
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .align(Alignment.TopCenter),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text(stringResource(id = R.string.find_out_more), color = Color.White)
                }
            }

        },

        ) {

        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = "thumbnail",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .aspectRatio(ratio = 0.7f, matchHeightConstraintsFirst = true),
                fallback = painterResource(id = R.drawable.marvel_default)
            )

        }
    }
}


