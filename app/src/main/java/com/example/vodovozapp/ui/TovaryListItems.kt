package com.example.vodovozapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.vodovozapp.R
import com.example.vodovozapp.data.model.Tovary
import com.example.vodovozapp.data.model.TovaryList

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun TovaryListItems(
    tovaryList: TovaryList
) {

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(0.dp),
    ) {

        val searchText = remember { mutableStateOf("") }
        // Состояние для хранения индекса выделенного текста
        var selectedIndex by remember { mutableStateOf(-1) }

        // Список текстов
//        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        val items = mutableListOf<String>()
        tovaryList.tovary.forEach { items.add(it.name) }

        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                query = searchText.value,
                onQueryChange = { },
                onSearch = { },
                placeholder = {
                    Text(stringResource(R.string.vodovoz_search))
                },
                active = false,
                onActiveChange = { },
                shadowElevation = 4.dp,
                trailingIcon = { },
            ) { }

            LazyRow {
                items(items.size) { index ->
                    // Проверяем, является ли текущий элемент выделенным
                    val isSelected = selectedIndex == index

                    // Выбор цвета текста: если элемент выделен, то цвет изменяется
                    val textColor = if (isSelected) Color.Red else Color.Black

                    AssistChip(
                        modifier = Modifier.padding(end = 8.dp),
                        onClick = {
                            selectedIndex = index
                        },
                        label = { Text(items[index]) },
                    )
                }
            }

            LazyRow {
                item(tovaryList.tovary) {
                    Card(
                        modifier = Modifier
                            .size(180.dp),
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(
//                            modifier = Modifier.size(38.dp),
                                    onClick = { }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = null,
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Surface {
                                    LoadImage(url = "https://szorin.vodovoz.ru/upload/iblock/9f4/xsd4uv427bs4rkrtb89qbpm0om8plehs.jpg")
//                        if (it.attachment?.type == AttachmentType.IMAGE) {
//                            LoadImage(url = it.attachment.url)
//                        }
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Text(
                                        text = "630",
                                        fontSize = 16.sp,
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Build,
                                        contentDescription = null,
                                    )
                                }
                                IconButton(
//                            modifier = Modifier.size(38.dp),
                                    onClick = { }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ShoppingCart,
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

@Composable
fun LoadImage(url: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        loading = { CircularProgressIndicator() },
//        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}

@Composable
fun HighlightableLazyRow() {
    // Состояние для хранения индекса выделенного текста
    var selectedIndex by remember { mutableStateOf(-1) }

    // Список текстов
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

    LazyRow {
        items(items.size) { index ->
            // Проверяем, является ли текущий элемент выделенным
            val isSelected = selectedIndex == index

            // Выбор цвета текста: если элемент выделен, то цвет изменяется
            val textColor = if (isSelected) Color.Red else Color.Black

            Text(
                text = items[index],
                color = textColor,
                modifier = Modifier
                    .clickable { selectedIndex = index }  // Обновляем индекс выделенного текста
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHighlightableLazyRow() {
    MaterialTheme {
        Surface {
            HighlightableLazyRow()
        }
    }
}