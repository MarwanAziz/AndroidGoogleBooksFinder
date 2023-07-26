package com.example.googlebooksfinder

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



private fun favouriteColor(viewModel: BooksListRowViewModel): Color {
    if(viewModel.favourite.value) return Color.Red
    return  Color.Gray
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BooksListRow(viewModel: BooksListRowViewModel) {
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 2f else 1f)

    Surface(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 100.dp)
                .height(120.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Transparent)
            ){
                BookImageView(
                    url = viewModel.thumbnailUrl,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 5.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(3f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = viewModel.title,
                    style = MaterialTheme.typography.h6,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSurface
                )

                Text(
                    text = viewModel.subtitle,
                    style = MaterialTheme.typography.body1,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSurface,
                    textAlign = TextAlign.Start
                )
                Text(text = "Authors: ${viewModel.author}",
                    maxLines = 1,
                    fontSize = 12.sp,)
                Text(text = "Publishing Date: ${viewModel.publishedDate}",
                    maxLines = 1,
                    fontSize = 12.sp,)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (viewModel.rate != null) {
                        Text(text = viewModel.rate,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(end = 5.dp))
                    }

                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()) {
                        if (viewModel.rate != null) {
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = null,
                                tint = colorResource(R.color.gold),
                                modifier = Modifier.size(20.dp)
                            )
                        } else {
                            Text(text = "")
                        }
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = null,
                            tint = favouriteColor(viewModel),
                            modifier = Modifier
                                .scale(scale.value)
                                .pointerInteropFilter {
                                    when (it.action) {
                                        MotionEvent.ACTION_DOWN -> {
                                            selected.value = true
                                        }

                                        MotionEvent.ACTION_UP -> {
                                            selected.value = false
                                            viewModel.onFavouriteClicked()
                                        }
                                    }
                                    true
                                },
                        )
                    }
                }
            }
        }
    }
}