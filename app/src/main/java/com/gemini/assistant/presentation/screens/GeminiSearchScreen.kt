package com.gemini.assistant.presentation.screens

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.flowWithLifecycle
import com.gemini.assistant.R
import com.gemini.assistant.presentation.composables.ChatFloatingActionButton
import com.gemini.assistant.presentation.composables.ContentSurface
import com.gemini.assistant.presentation.composables.CustomPermissionDialog
import com.gemini.assistant.presentation.composables.GeminiResponseHandlerComposable
import com.gemini.assistant.presentation.events.GeminiSearchEvent
import com.gemini.assistant.presentation.states.GeminiSearchState
import com.gemini.assistant.utils.Constants
import com.gemini.assistant.utils.Constants.EXTERNAL_STORAGE_PERMISSION
import com.gemini.assistant.utils.Constants.MEDIA_IMAGES_PERMISSION
import com.gemini.assistant.utils.Constants.MEDIA_VISUAL_USER_SELECTED_PERMISSION
import com.gemini.assistant.utils.GeminiResult
import com.gemini.assistant.utils.helpers.compressBitmap
import com.gemini.assistant.utils.helpers.isKeyboardOpen
import com.gemini.assistant.utils.helpers.parseUriToBitmap
import com.gemini.assistant.utils.helpers.permission.ExternalStoragePermissionProvider
import com.gemini.assistant.utils.helpers.permission.MediaImagesPermissionProvider
import com.gemini.assistant.utils.helpers.permission.MediaVisualUserSelectedPermissionProvider
import com.gemini.assistant.utils.helpers.permission.goToAppSettings
import com.gemini.assistant.utils.helpers.permission.isShouldShowRequestPermissionRationale
import com.gemini.assistant.utils.internet_connection.ConnectivityStatus
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
@Composable
fun GeminiSearchScreen(
    geminiSearchState: GeminiSearchState,
    connectivityStatus: ConnectivityStatus,
    onGeminiSearchEvent: (GeminiSearchEvent) -> Unit,
    isShowScrollDownButton: Boolean
) {

    val searchRequestModel = geminiSearchState.searchRequestModel
    val searchResponseModel = geminiSearchState.searchResponseModel
    val visiblePermissionDialogQueue = geminiSearchState.visiblePermissionDialogQueue

    var isSearchTextFieldFocused by rememberSaveable {
        mutableStateOf(false)
    }

    val isKeyboardOpen by isKeyboardOpen()

    val focusManager = LocalFocusManager.current

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val coroutineScope = rememberCoroutineScope()

    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val columnMaxHeight = scrollState.maxValue

    LaunchedEffect(key1 = isKeyboardOpen) {
        if (!isKeyboardOpen && isSearchTextFieldFocused) focusManager.clearFocus()
    }

    LaunchedEffect(key1 = scrollState.canScrollForward) {
        val isLoading = searchResponseModel is GeminiResult.Loading
        val isFloatingActionButtonShow = scrollState.canScrollForward && !isLoading
        snapshotFlow { isFloatingActionButtonShow }
            .distinctUntilChanged()
            .debounce(Constants._300L)
            .flowWithLifecycle(lifecycle)
            .collectLatest { canScrollForward ->
                if (canScrollForward) onGeminiSearchEvent(GeminiSearchEvent.IsShowScrollDownButtonUpdate(true))
                else onGeminiSearchEvent(GeminiSearchEvent.IsShowScrollDownButtonUpdate(false))
            }
    }

    val galleryPickerLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickMultipleVisualMedia()) { uris ->
        if (uris.isNotEmpty()) {
            val bitmaps = mutableListOf<Bitmap>()
            uris.forEach { uri ->
                val bitmap = uri.parseUriToBitmap(context = context)?.compressBitmap()
                bitmap?.let {
                    bitmaps.add(it)
                }
            }
            onGeminiSearchEvent(GeminiSearchEvent.OnSearchRequestImagesUpdate(searchRequestImages = bitmaps.toList()))
        }
    }

    val multiplePermissionsGalleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
        val areAllPermissionsGranted = permissionsMap.values.all { it }
        if (areAllPermissionsGranted) galleryPickerLauncher.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
        permissionsMap.keys.forEach { permission ->
            onGeminiSearchEvent(GeminiSearchEvent.OnPermissionResult(permission, permissionsMap[permission] == true))
        }
    }

    visiblePermissionDialogQueue
        .reversed()
        .forEach { permission ->
            CustomPermissionDialog(
                permissionProvider = when(permission) {
                    EXTERNAL_STORAGE_PERMISSION -> ExternalStoragePermissionProvider()
                    MEDIA_IMAGES_PERMISSION -> MediaImagesPermissionProvider()
                    MEDIA_VISUAL_USER_SELECTED_PERMISSION -> MediaVisualUserSelectedPermissionProvider()
                    else -> return@forEach
                },
                isPermanentlyDeclined = !isShouldShowRequestPermissionRationale(context, permission),
                onDismissClick = {
                    onGeminiSearchEvent(GeminiSearchEvent.OnDismissPermissionDialog)
                },
                onOkClick = {
                    onGeminiSearchEvent(GeminiSearchEvent.OnDismissPermissionDialog)
                    multiplePermissionsGalleryLauncher.launch(arrayOf(permission))
                },
                onGoToAppSettingsClick = {
                    context.goToAppSettings()
                }
            )
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .weight(Constants._08f)
                .padding(
                    start = dimensionResource(id = R.dimen._10dp),
                    end = dimensionResource(id = R.dimen._10dp),
                    bottom = dimensionResource(id = R.dimen._10dp)
                ),
            floatingActionButton = {
                AnimatedVisibility(
                    visible = isShowScrollDownButton,
                    enter = slideInVertically(
                        animationSpec = tween(durationMillis = Constants._500),
                        initialOffsetY = { columnMaxHeight }),
                    exit = slideOutVertically(
                        animationSpec = tween(durationMillis = Constants._500),
                        targetOffsetY = { columnMaxHeight })
                ) {
                    ChatFloatingActionButton(
                        onClick = {
                            coroutineScope.launch {
                                scrollState.animateScrollTo(columnMaxHeight)
                                onGeminiSearchEvent(
                                    GeminiSearchEvent.IsShowScrollDownButtonUpdate(
                                        false
                                    )
                                )
                            }
                        }
                    )
                }
            }
        ) { paddingValues ->
            ContentSurface(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                AnimatedContent(
                    targetState = searchResponseModel,
                    label = ""
                ) { geminiResponse ->
                    GeminiResponseHandlerComposable(geminiResponse = geminiResponse)
                }
            }
        }
    }
}