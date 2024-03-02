package com.gemini.assistant.presentation.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.gemini.assistant.R
import com.gemini.assistant.utils.helpers.permission.PermissionProvider

@Composable
fun CustomPermissionDialog(
    permissionProvider: PermissionProvider,
    isPermanentlyDeclined: Boolean,
    onDismissClick: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit
) {

    var isEndScalingIconAnimation by rememberSaveable {
        mutableStateOf(false)
    }

    val animateIconScale by animateFloatAsState(
        targetValue = if (isEndScalingIconAnimation) 2f else 0f,
        label = ""
    )

    val context = LocalContext.current

    if (!isEndScalingIconAnimation) {
        LaunchedEffect(key1 = Unit) {
            isEndScalingIconAnimation = true
        }
    }

    val onBackgroundColor = MaterialTheme.colorScheme.onBackground

    AlertDialog(
        onDismissRequest = onDismissClick,
        confirmButton = {
            CustomTextButton(
                onTextButtonClick = if (isPermanentlyDeclined) onGoToAppSettingsClick else onOkClick,
                text = if (isPermanentlyDeclined) stringResource(id = R.string.permanentlyDeclinedButtonText)
                    else stringResource(id = R.string.ok),
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = onBackgroundColor
            )
        },
        dismissButton = {
            CustomTextButton(
                onTextButtonClick = onDismissClick,
                text = stringResource(id = R.string.decline),
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )                
        },
        icon = {
            Icon(
                modifier = Modifier.graphicsLayer {
                    scaleX = animateIconScale
                    scaleY = animateIconScale
                },
                imageVector = permissionProvider.permissionIcon(),
                tint = onBackgroundColor,
                contentDescription = null
            )
        },
        title = {
            CustomText(
                text = stringResource(id = R.string.titlePermission),
                textStyle = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        },
        text = {
            CustomText(
                text = permissionProvider.permissionDescription(context, isPermanentlyDeclined),
                textStyle = MaterialTheme.typography.bodyLarge
            )
        },
        containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
        iconContentColor = onBackgroundColor,
        titleContentColor = onBackgroundColor,
        textContentColor = onBackgroundColor
    )
}