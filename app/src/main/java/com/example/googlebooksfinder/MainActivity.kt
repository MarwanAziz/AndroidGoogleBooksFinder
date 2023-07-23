package com.example.googlebooksfinder

import apiServices.ApiServices
import apiServices.ApiServicesImp
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.googlebooksfinder.ui.theme.GoogleBooksFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleBooksFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android", applicationContext)
                }
            }
        }
    }
}
fun getData(context: Context?) {
    if (context == null) return

    val apiServices: ApiServices = ApiServicesImp(context)
    apiServices.searchBooks("the+love") {
        print(it.searchResult)
    }
}

@Composable
fun Greeting(name: String, context: Context?) {
    Text(
        "Hello $name!",
        textAlign = TextAlign.Center,
        fontSize = 24.sp,

        modifier = Modifier
            .fillMaxSize()
            .clickable {
                getData(context)
            }

    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GoogleBooksFinderTheme {
        Greeting("Android", context = null)
    }
}