package com.example.pokeapi.list

import android.widget.ScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokeapi.R
import com.example.pokeapi.common.ErrorState
import kotlinx.coroutines.flow.collect

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pagingData = viewModel.pagingData.collectAsLazyPagingItems()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn {
                // Iterate through paging data list
                items(pagingData.itemCount) {
                    val name = pagingData[it]?.name ?: ""
                    PokemonCell(
                        index = "${it+1}",
                        name = name
                    ) {
                        navController.navigate("details/$name")
                    }
                }
                // Check refreshing state for paging data
                pagingData.apply {
                    when {
                        // FIRST LOAD
                        loadState.refresh is LoadState.Loading -> {
                            item { CircularProgressIndicator() }
                        }

                        // GOT ERROR ON FIRST LOAD
                        loadState.refresh is LoadState.Error -> {
                            item {
                                ErrorState()
                            }
                        }

                        // LOADING A NEXT PAGE
                        loadState.append is LoadState.Loading -> {
                            item { CircularProgressIndicator() }
                        }

                        // GOT AN ERROR AFTER LOADING SOME SUBSEQUENT PAGE
                        loadState.append is LoadState.Error -> {
                            item {
                                ErrorState()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PokemonCell(
    modifier: Modifier = Modifier,
    index: String,
    name: String,
    onClick: () -> Unit
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(top = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start) {
        Row(
            modifier = Modifier.padding(start = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = index, fontSize = 20.sp)
            Text(text = name, fontSize = 20.sp, modifier = Modifier.padding(start = 16.dp))
        }
        Divider(color = Color.Gray, modifier = Modifier.padding(top = 20.dp))
    }
}