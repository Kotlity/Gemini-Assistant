package com.gemini.assistant.presentation.composables

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import com.gemini.assistant.R

@Composable
fun SearchRequestImageSection(
    modifier: Modifier = Modifier,
    image: Bitmap,
    onDeleteIconClick: () -> Unit,
    onExpandIconClick: () -> Unit
) {

    val iconModifier = Modifier
        .size(dimensionResource(id = R.dimen._24dp))

    ContentBox(modifier = modifier) {
        CustomAsyncImage(
            data = image,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = -dimensionResource(id = R.dimen._5dp)),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            DeleteImageIcon(
                modifier = iconModifier,
                onDeleteIconClick = onDeleteIconClick
            )
            ExpandImageIcon(
                modifier = iconModifier,
                onExpandIconClick = onExpandIconClick
            )
        }
    }
}