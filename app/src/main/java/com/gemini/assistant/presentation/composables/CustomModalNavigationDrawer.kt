package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomModalNavigationDrawer(
    navigationDrawerItemInfo: NavigationDrawerItemInfo,
    drawerState: DrawerState,
    gesturesEnabled: Boolean,
    onItemClick: (String) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerContent = {
            CustomNavigationDrawerItem(
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                navigationDrawerItemInfo = navigationDrawerItemInfo,
                onItemClick = onItemClick
            )
        },
        drawerState = drawerState,
        gesturesEnabled = gesturesEnabled,
        content = content
    )
}