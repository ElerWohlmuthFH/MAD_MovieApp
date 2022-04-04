package com.example.mad_movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import com.example.mad_movieapp.navigation.MovieNavigation
import com.example.mad_movieapp.screens.home.HomeScreen
import com.example.mad_movieapp.ui.theme.MAD_MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background)
                {
                    MovieNavigation()
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){

   MAD_MovieAppTheme() {
        content()
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        HomeScreen()
    }
}

