package com.gemini.assistant.presentation.composables

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import com.gemini.assistant.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchRequestImageLazyRow(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = dimensionResource(id = R.dimen._10dp), vertical = dimensionResource(id = R.dimen._5dp)),
    images: List<Bitmap>,
    onDeleteIconClick: (Bitmap) -> Unit,
    onExpandIconClick: (Bitmap) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(
            images,
            key = { it.generationId }
        ) { image ->
            SearchRequestImageSection(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen._90dp))
                    .shadow(
                        elevation = dimensionResource(id = R.dimen._1dp),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen._10dp))
                    )
                    .padding(end = dimensionResource(id = R.dimen._5dp))
                    .animateItemPlacement(),
                image = image,
                onDeleteIconClick = {
                    onDeleteIconClick(image)
                },
                onExpandIconClick = {
                    onExpandIconClick(image)
                }
            )
        }
    }
}