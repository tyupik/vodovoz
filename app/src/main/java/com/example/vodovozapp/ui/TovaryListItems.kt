package com.example.vodovozapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.vodovozapp.R
import com.example.vodovozapp.data.model.Status
import com.example.vodovozapp.data.model.TovaryList

@Composable
fun TovaryListItems(
    tovaryList: TovaryList,
    selectedChipIndex: Int,
    onChipClick: (Int) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (tovaryList.status == Status.SUCCESS) {
            Chips(
                items = tovaryList.tovary.map { it.name },
                onChipClick = onChipClick
            )

            tovaryList.tovary[selectedChipIndex].let { tovars ->

                LazyRow {
                    itemsIndexed(tovars.data) { index, item ->
                        Card(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(horizontal = 4.dp),
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                            colors = CardColors(Color.White, Color.Black, Color.White, Color.White)
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(180.dp)
                                    .padding(8.dp),
                            ) {
                                Box {
                                    LoadImage(url = "https://szorin.vodovoz.ru/${item.imageUrl}")
                                    Row {
                                        Spacer(modifier = Modifier.weight(1f))
                                        IconButton(
                                            onClick = { }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.FavoriteBorder,
                                                contentDescription = null,
                                            )
                                        }
                                    }
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = item.extendedPrice.first().price.toString() + " ₽",
                                        modifier = Modifier.padding(end = 4.dp)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    IconButton(
                                        modifier = Modifier.size(38.dp),
                                        onClick = { }
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.backet),
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadImage(url: String) {
    SubcomposeAsyncImage(
        modifier = Modifier.size(180.dp),
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        loading = { CircularProgressIndicator() },
        contentDescription = null,
    )
}

@Composable
private fun Chips(
    items: List<String>,
    onChipClick: (Int) -> Unit,
) {
    var selectedIndex by remember { mutableIntStateOf(0) }


    LazyRow {
        items(items.size) { index ->
            val isSelected = selectedIndex == index
            val textColor = if (isSelected) Color(0xFF00A1E9) else Color.Black

            AssistChip(
                modifier = Modifier.padding(horizontal = 4.dp),
                border = AssistChipDefaults.assistChipBorder(false),
                onClick = {
                    selectedIndex = index
                    onChipClick(index)
                },
                label = {
                    Text(
                        text = items[index],
                        color = textColor
                    )
                }
            )
        }
    }
}