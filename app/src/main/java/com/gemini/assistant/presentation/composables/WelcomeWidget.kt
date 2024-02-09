package com.gemini.assistant.presentation.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.gemini.assistant.R
import com.gemini.assistant.utils.Constants._0f
import com.gemini.assistant.utils.Constants._18sp
import com.gemini.assistant.utils.Constants._1f

@Composable
fun WelcomeWidget(
    modifier: Modifier = Modifier,
    verticalPadding: Dp = dimensionResource(id = R.dimen._10dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(verticalPadding, Alignment.CenterVertically),
    imageVector: ImageVector = Icons.Default.Computer,
    text: String = stringResource(id = R.string.welcome)
) {

    var alphaTriggerStarter by rememberSaveable {
        mutableStateOf(false)
    }

    val welcomeWidgetAlphaAnimation by animateFloatAsState(
        targetValue = if (alphaTriggerStarter) _1f else _0f,
        label = "",
        animationSpec = tween(durationMillis = integerResource(id = R.integer._2000))
    )

    LaunchedEffect(key1 = Unit) {
        alphaTriggerStarter = true
    }

    val imageBorder = BorderStroke(
        width = dimensionResource(id = R.dimen._2dp),
        color = MaterialTheme.colorScheme.onSecondary
    )

    Column(
        modifier = modifier.graphicsLayer {
            alpha = welcomeWidgetAlphaAnimation
        },
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement
    ) {
        CustomImageVector(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen._150dp))
                .clip(CircleShape)
                .border(imageBorder, CircleShape),
            imageVector = imageVector
        )
        CustomText(
            text = text,
            textStyle = LocalTextStyle.current.copy(
                fontSize = _18sp,
                fontWeight = FontWeight.W400,
                fontStyle = FontStyle.Italic
            ),
            textAlign = TextAlign.Center
        )
    }
}