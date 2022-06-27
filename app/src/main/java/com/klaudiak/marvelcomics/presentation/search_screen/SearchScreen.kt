package com.klaudiak.marvelcomics.presentation.search_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klaudiak.marvelcomics.R
import com.klaudiak.marvelcomics.presentation.comics_list.ComicListViewModel
import com.klaudiak.marvelcomics.presentation.bar.BottomNavigationBar
import com.klaudiak.marvelcomics.ui.theme.Gray20


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicSearchScreen(
    navController: NavController,
    viewModel: ComicListViewModel = hiltViewModel<ComicListViewModel>()
) {
    val uiState = viewModel.uiState

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) },
        content =
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row{
                    SearchBar(
                        hint = "Search for a comic book"
                    )
                }

                if (uiState.value.searchedItems?.isEmpty() == true && uiState.value.query == ""){
                    Surface(modifier = Modifier.fillMaxSize(),  color = Gray20) {
                        HintSearchBar()
                    }
                }

                else if(uiState.value.query != "" && uiState.value.loadError == "No data"){
                    Log.i("Results nooo", uiState.value.loadError.toString())
                    Surface(modifier = Modifier.fillMaxSize(), color = Gray20) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                            Text(text = "No results", fontSize = 38.sp)
                        }
                    }
                }


                else{
                    ComicList(
                        comicList = uiState.value.searchedItems,
                        navController = navController
                    )

                }

            }
        })
}


@Composable
fun HintSearchBar() {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.book_icon),
                contentDescription = null
            )
            Text(
                text = "Start typing to find a particular comics",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
}

@Composable
fun SearchBar(
    hint: String = "",
    viewModel: ComicListViewModel = hiltViewModel<ComicListViewModel>()
) {
    val uiState = viewModel.uiState
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ){

    Box{
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                viewModel.onEvent(SearchScreenEvent.OnQueryChanged(it))
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth(
                    if (text.isNotEmpty()) {
                        0.7f
                    } else {
                        1f
                    }
                )
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = text.isEmpty()

                }
                .focusTarget()
        )

        androidx.compose.animation.AnimatedVisibility(visible = text.isEmpty()) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }

    }
        androidx.compose.animation.AnimatedVisibility(visible = text.isNotEmpty()) {
            TextButton(
                onClick = { text = "" }, modifier = Modifier.padding(start = 6.dp)

            ) {
                Text(text = "Cancel", fontSize = 18.sp, color = Color.Black)
            }
        }


    }

}