package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gemini.assistant.R
import com.gemini.assistant.utils.helpers.GeminiChatTopAppBarTitle
import com.gemini.assistant.utils.helpers.GeminiMessageTopAppBarTitle
import com.gemini.assistant.utils.helpers.Navigator
import com.gemini.assistant.utils.helpers.ScreenRoutes
import kotlinx.coroutines.launch

@Composable
fun GeneralContentSurface(
    navHostController: NavHostController,
    navigator: Navigator,
    content: @Composable (Modifier) -> Unit
) {

    val currentDestination = navHostController.currentBackStackEntryAsState().value?.destination?.route
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    CustomModalNavigationDrawer(
        navigationDrawerItemInfo = when(currentDestination) {
            ScreenRoutes.GeminiChat.route -> NavigationDrawerItemInfo(icon = Icons.Default.Message, title = stringResource(id = R.string.gemini_message_top_app_bar_title))
            ScreenRoutes.GeminiMessage.route -> NavigationDrawerItemInfo(icon = Icons.Default.ChatBubbleOutline, title = stringResource(id = R.string.gemini_chat_top_app_bar_title))
            else -> NavigationDrawerItemInfo(icon = Icons.Default.ChatBubbleOutline, title = stringResource(id = R.string.gemini_chat_top_app_bar_title))
        },
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        onItemClick = { screenRoute ->
            coroutineScope.launch {
                drawerState.close()
            }
            navigator.navigate(screenRoute)
        },
        content = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    CustomTopAppBar(
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(id = R.dimen._10dp))
                            .clip(RoundedCornerShape(dimensionResource(id = R.dimen._20dp))),
                        topAppBarTitleProvider = when(currentDestination) {
                            ScreenRoutes.GeminiChat.route -> GeminiChatTopAppBarTitle()
                            ScreenRoutes.GeminiMessage.route -> GeminiMessageTopAppBarTitle()
                            else -> GeminiChatTopAppBarTitle()
                        },
                        onNavigationIconClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
            ) { paddingValues ->
                content(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }
    )
}