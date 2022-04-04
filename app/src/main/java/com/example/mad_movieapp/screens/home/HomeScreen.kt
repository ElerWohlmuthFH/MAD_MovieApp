package com.example.mad_movieapp.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_movieapp.models.Movie
import com.example.mad_movieapp.models.getMovies
import com.example.mad_movieapp.navigation.MovieScreens
import com.example.mad_movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {

    var menu by remember {
        mutableStateOf(false)
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { menu = !menu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }

                    DropdownMenu(expanded = menu, onDismissRequest = { menu = false }) {
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorites",
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    text = "Favorites",
                                    modifier = Modifier.padding(4.dp).width(100.dp)
                                )
                            }
                        }
                    }
                }
            )
        }
    ){
        MainContent(navController = navController, movieList = getMovies())
    }
}

@Composable
fun MainContent(navController: NavController, movieList: List<Movie>) {

    LazyColumn{
        items(movieList) { movie ->
            MovieRow(movie = movie){ movieId ->
                navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")

            }
        }
    }
}
