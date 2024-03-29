package com.gemini.assistant.presentation.composables

import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import com.gemini.assistant.R
import com.gemini.assistant.utils.Constants._1_5f
import com.gemini.assistant.utils.Constants._1f

@Composable
fun TypingAnimationWidget(
    modifier: Modifier = Modifier
) {

    val infiniteTransaction = rememberInfiniteTransition(label = "")

    val dotCountInfiniteAnimation by infiniteTransaction.animateValue(
        initialValue = integerResource(id = R.integer._1),
        targetValue = integerResource(id = R.integer._4),
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = integerResource(id = R.integer._1500))
        ),
        label = ""
    )

    val dotScaleInfiniteAnimation by infiniteTransaction.animateFloat(
        initialValue = _1f,
        targetValue = _1_5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = integerResource(id = R.integer._1500))
        ),
        label = ""
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen._5dp),
            Alignment.Start
        )
    ) {
        Icon(
            modifier = Modifier.size(dimensionResource(id = R.dimen._35dp)),
            imageVector = Icons.Default.Create,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null
        )
        repeat(dotCountInfiniteAnimation) {
            Icon(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen._10dp))
                    .graphicsLayer {
                        scaleX = dotScaleInfiniteAnimation
                        scaleY = dotScaleInfiniteAnimation
                    },
                imageVector = Icons.Default.Circle,
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = null
            )
        }
    }
}