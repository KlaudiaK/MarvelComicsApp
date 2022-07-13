package com.klaudiak.marvelcomics.presentation.bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.klaudiak.marvelcomics.ui.theme.White40

@Composable
fun TopAppBar(navController: NavController, text: String) {
    SmallTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = White40),
        title = {
            Text(
                text = text,
                fontSize = 32.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {

                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }

            } else {
                null
            }
        })

}