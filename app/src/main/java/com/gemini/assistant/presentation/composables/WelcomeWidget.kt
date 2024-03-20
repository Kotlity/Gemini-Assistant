package com.gemini.assistant.presentation.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.gemini.assistant.R
import com.gemini.assistant.utils.Constants._0f
import com.gemini.assistant.utils.Constants._16sp
import com.gemini.assistant.utils.Constants._1f

@Composable
fun WelcomeWidget(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    imageVector: ImageVector = Icons.Default.Computer,
    text: String = stringResource(id = R.string.chat_search_welcome)
) {

    var alphaTriggerStarter by rememberSaveable {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (alphaTriggerStarter) _1f else _0f,
        label = "",
        animationSpec = tween(durationMillis = integerResource(id = R.integer._2000))
    )

    LaunchedEffect(key1 = Unit) {
        if (!alphaTriggerStarter) alphaTriggerStarter = true
    }

    Column(
        modifier = modifier.graphicsLayer {
            alpha = alphaAnimation
        },
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement
    ) {
        CustomImageVector(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen._150dp))
            ,
            imageVector = imageVector,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        CustomText(
            text = text,
            textStyle = LocalTextStyle.current.copy(
                fontSize = _16sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center
        )
    }
}