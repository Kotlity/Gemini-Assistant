package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.gemini.assistant.R
import com.gemini.assistant.utils.Constants._18sp

@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(dimensionResource(id = R.dimen._2dp), Alignment.Start),
    isGeminiTitle: Boolean = true,
    title: String,
    icon: Painter
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        if (isGeminiTitle) {
            TitleIcon(icon = icon)
            TitleText(title = title)
        } else {
            TitleText(title = title)
            TitleIcon(
                icon = icon,
                isGeminiIcon = false
            )
        }
    }
}

@Composable
private fun TitleIcon(
    icon: Painter,
    isGeminiIcon: Boolean = true
) {

    if (isGeminiIcon) {
        Icon(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen._25dp)),
            painter = icon,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null
        )
    } else {
        val borderStroke = BorderStroke(
            width = dimensionResource(id = R.dimen._1dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        Icon(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen._25dp))
                .border(
                    border = borderStroke,
                    shape = CircleShape
                )
                .padding(dimensionResource(id = R.dimen._2dp))
                .clip(CircleShape),
            painter = icon,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null
        )
    }
}

@Composable
private fun TitleText(
    title: String
) {
    CustomText(
        text = title,
        textStyle = MaterialTheme.typography.titleMedium.copy(
            fontSize = _18sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    )
}