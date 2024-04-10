package com.example.plantcareai.AiCamera

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plantcareai.AiCamera.BitmapConverter.convertStringToBitmap
import com.example.plantcareai.R
import com.example.plantcareai.model.ClassifiedImage
import kotlinx.coroutines.launch


@Composable
fun History(viewModel: MainViewModel = hiltViewModel()) {
    val classifiedImages = viewModel.classifiedImages.collectAsState().value
    val textState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val searchedText = textState.value.text
    val filteredClassifiedImages = classifiedImages.filter {
        it.label.contains(searchedText, ignoreCase = true)
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 12.dp)
    ) {

        SearchBar(state = textState)

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn() {
            items(items = filteredClassifiedImages) { it ->
                HistoryCards(
                    it,
                    onDelete = {
                        scope.launch {
                            viewModel.onDelete(it)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

        }

    }
}

@Composable
fun HistoryCards(
    classifiedImage: ClassifiedImage,
    onDelete: (ClassifiedImage) -> Unit
) {
    val bitmap = convertStringToBitmap(classifiedImage.name)
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primaryContainer)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                bitmap.asImageBitmap(),
                contentDescription = classifiedImage.label,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
            ) {
                Text(
                    text = classifiedImage.label,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(
                        id = R.string.confidence,
                        (classifiedImage.confidence * 100).toString()
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { onDelete(classifiedImage) })
                    {
                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete Icon", tint = Color(0xFF0D6446))

                    }

                }
            }

        }
    }
}

@Composable
fun SearchBar(state: MutableState<TextFieldValue>) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.value,
        onValueChange = { value -> state.value = value },
        placeholder = { Text("Search") },
        shape = RoundedCornerShape(10.dp),
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search Icon", tint = Color(0xFF0D6446))
        }
    )
}

@Preview
@Composable
fun HistoryPreview() {
    History()
}