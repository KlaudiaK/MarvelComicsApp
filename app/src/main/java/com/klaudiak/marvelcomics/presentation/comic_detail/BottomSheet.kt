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
import androidx.compose.ui.res.dimensionResource
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
        sheetShape = RoundedCornerShape(topStart = dimensionResource(id = R.dimen.bottom_sheet_shape), topEnd = dimensionResource(id = R.dimen.bottom_sheet_shape)),
        sheetContentColor = Color.Transparent,
        sheetPeekHeight = dimensionResource(id = R.dimen.bottom_sheet_peek_heigh),
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.medium_box_heigh))
                    .padding(top = dimensionResource(id = R.dimen.small_padding))
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            ) {
                Button(
                    onClick = {
                        scope.launch { scaffoldState.bottomSheetState.collapse() }
                    },
                    modifier = Modifier.height(dimensionResource(id = R.dimen.quite_small_padding)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                ) {}
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.bottom_sheet_peek_heigh))
                    .background(Color.Transparent),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .verticalScroll(columnScrollState)
                        .background(Color.Transparent)
                        .padding(dimensionResource(id = R.dimen.extra_extra_large_padding)),
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = comic.title,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        fontSize = dimensionResource(id = R.dimen.title_font_size).value.sp
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_spacer_heigh)))
                    Text(
                        text = comic.author,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        fontSize = dimensionResource(id = R.dimen.author_font_size).value.sp
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_spacer_heigh)))
                    Text(
                        text = description,
                        fontWeight = FontWeight.W300,
                        textAlign = TextAlign.Start,
                        fontSize = dimensionResource(id = R.dimen.description_font_size).value.sp
                    )
                }


            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.extra_medium_box_heigh))
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
                        .height(dimensionResource(id = R.dimen.large_bottom_heigh))
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


