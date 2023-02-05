package com.example.hewa.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hewa.R
import com.example.hewa.ui.components.WeatherBackground
import com.example.hewa.ui.theme.Gold

@Composable
fun SearchScreen(onSearchClick: (String) -> Unit) {
    WeatherBackground {
        SearchItem(onSearchClick)
    }
}

@Composable
private fun SearchItem(onSearchClick: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            val (text, setText) = remember {
                mutableStateOf("")
            }
            OutlinedTextField(value = text, onValueChange = setText)
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = {
                    onSearchClick.invoke(text)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Gold),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "Search")
            }

        }
    }
}