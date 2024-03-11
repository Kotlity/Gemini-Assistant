package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.gemini.assistant.R

@Composable
fun CustomModalNavigationDrawer(
    navigationDrawerItemInfo: NavigationDrawerItemInfo,
    drawerState: DrawerState,
    gesturesEnabled: Boolean,
    onItemClick: (String) -> Unit,
    content: @Composable () -> Unit
) {

    val contentPadding = dimensionResource(id = R.dimen._20dp)

    ModalNavigationDrawer(
        drawerContent = {
            CustomModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(
                    topEnd = contentPadding,
                    bottomEnd = contentPadding
                ),
                content = {
                    CustomNavigationDrawerItem(
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                            .padding(top = contentPadding)
                        ,
                        navigationDrawerItemInfo = navigationDrawerItemInfo,
                        onItemClick = onItemClick
                    )
                }
            )
        },
        drawerState = drawerState,
        gesturesEnabled = gesturesEnabled,
        content = content
    )
}