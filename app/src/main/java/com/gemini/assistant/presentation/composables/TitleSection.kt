package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.gemini.assistant.R
import com.gemini.assistant.utils.Constants._18sp

@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(dimensionResource(id = R.dimen._2dp), Alignment.Start),
    isGeminiTitle: Boolean = true,
    title: String,
    image: String? = null,
    onIconClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        if (isGeminiTitle) {
            TitleIcon()
            TitleText(title = title)
        } else {
            TitleText(title = title)
            TitleIcon(
                image = image,
                isGeminiIcon = false,
                onIconClick = onIconClick
            )
        }
    }
}

@Composable
private fun TitleIcon(
    image: String? = null,
    isGeminiIcon: Boolean = true,
    onIconClick: (() -> Unit)? = null
) {

    if (isGeminiIcon) {
        Icon(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen._25dp)),
            painter = painterResource(id = R.drawable.gemini_logo),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null
        )
    } else {
        val borderStroke = BorderStroke(
            width = dimensionResource(id = R.dimen._1dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        val userIconModifier = Modifier
            .size(dimensionResource(id = R.dimen._25dp))
            .padding(dimensionResource(id = R.dimen._2dp))
            .clip(CircleShape)
            .border(
                border = borderStroke,
                shape = CircleShape
            )
            .clickable { onIconClick?.let { it() } }

        if (image == null) {
            Icon(
                modifier = userIconModifier,
                painter = painterResource(id = R.drawable.user_default_logo),
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = null
            )
        } else {
            CustomAsyncImage(
                modifier = userIconModifier,
                data = image
            )
        }
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