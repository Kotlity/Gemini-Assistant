package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.gemini.assistant.R

@Composable
fun CustomNavigationDrawerItem(
    modifier: Modifier = Modifier,
    navigationDrawerItemInfo: NavigationDrawerItemInfo,
    shapeDp: Dp = dimensionResource(id = R.dimen._20dp),
    navigationDrawerItemShape: Shape = RoundedCornerShape(shapeDp),
    onItemClick: (String) -> Unit
) {

    val containerColor = MaterialTheme.colorScheme.primary
    val contentColor = MaterialTheme.colorScheme.onPrimary

    NavigationDrawerItem(
        modifier = modifier,
        label = {
            Text(
                text = navigationDrawerItemInfo.title,
                style = MaterialTheme.typography.labelLarge
            )
        },
        icon = {
            Icon(
                imageVector = navigationDrawerItemInfo.icon,
                contentDescription = null
            )
        },
        shape = navigationDrawerItemShape,
        selected = false,
        onClick = { onItemClick(navigationDrawerItemInfo.title) },
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = containerColor,
            unselectedContainerColor = containerColor,
            selectedIconColor = contentColor,
            unselectedIconColor = contentColor,
            selectedTextColor = contentColor,
            unselectedTextColor = contentColor
        )
    )
}

data class NavigationDrawerItemInfo(val icon: ImageVector, val title: String)