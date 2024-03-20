package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.gemini.assistant.R
import com.gemini.assistant.domain.model.SearchResponseModel
import com.gemini.assistant.utils.Constants

@Composable
fun SearchResponseSection(
    modifier: Modifier = Modifier,
    searchResponseModel: SearchResponseModel,
    onTextCopied: ((String) -> Unit)? = null
) {
    Column(modifier = modifier) {
        CustomText(
            text = searchResponseModel.searchResponse,
            onTextCopied = onTextCopied,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                fontSize = Constants._16sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        searchResponseModel.searchResponseImage?.let { bitmap ->
            CustomAsyncImage(
                modifier = Modifier.size(dimensionResource(id = R.dimen._200dp)),
                data = bitmap
            )
        }
    }
}