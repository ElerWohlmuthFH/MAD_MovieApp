package com.example.mad_movieapp.widgets


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.mad_movieapp.models.Movie
import com.example.mad_movieapp.models.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie,
             onItemClick: (String) -> Unit = {}
) {

    var info by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier.height(130.dp).fillMaxWidth().padding(4.dp).clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.padding(12.dp).size(100.dp),
                shape = RectangleShape,
                elevation = 6.dp
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0],
                        builder = {
                            transformations(CircleCropTransformation())
                        }

                    ),
                    contentDescription = "Movie Poster",
                )
            }
            Column {
                Text(text = movie.title)
                Text(text = "Director: " + movie.director)
                Text(text = "Released: " + movie.year)

                IconButton(onClick = { info = !info }) {
                    Icon(imageVector = if (info) {
                        Icons.Default.KeyboardArrowUp

                    } else {
                        Icons.Default.KeyboardArrowDown
                    },

                        contentDescription = "Infos",
                        Modifier.clickable { info = !info })
                }
                DropdownMenu(expanded = info, onDismissRequest = { info = false }) {
                    DropdownMenuItem(onClick = { /*TODO*/ }) {
                        Column {

                            Column {
                                Text("Plot: " + movie.plot, Modifier.fillMaxSize())
                                Divider(startIndent = 5.dp)
                                Text("Genre: " + movie.genre, Modifier.fillMaxSize())
                                Text("Actors: " + movie.actors, Modifier.fillMaxSize())
                                Text("Rating: " + movie.rating, Modifier.fillMaxSize())
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]){
    LazyRow{

        items(movie.images){ image ->
            Card(
                modifier = Modifier.padding(12.dp).size(240.dp),
                elevation = 4.dp
            ) {
                Image(painter = rememberImagePainter(data = image),
                    contentDescription = "movie image")
            }
        }
    }
}
