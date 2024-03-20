package com.gemini.assistant.presentation.screens

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.flowWithLifecycle
import com.gemini.assistant.R
import com.gemini.assistant.domain.model.SearchResponseModel
import com.gemini.assistant.presentation.composables.ChatFloatingActionButton
import com.gemini.assistant.presentation.composables.ConnectionInfoWidget
import com.gemini.assistant.presentation.composables.ContentSurface
import com.gemini.assistant.presentation.composables.CustomDivider
import com.gemini.assistant.presentation.composables.CustomPermissionDialog
import com.gemini.assistant.presentation.composables.ErrorSection
import com.gemini.assistant.presentation.composables.ImagePickerIcon
import com.gemini.assistant.presentation.composables.LoadingSection
import com.gemini.assistant.presentation.composables.SearchRequestImageLazyRow
import com.gemini.assistant.presentation.composables.SearchResponseSection
import com.gemini.assistant.presentation.composables.SearchTextField
import com.gemini.assistant.presentation.composables.WelcomeWidget
import com.gemini.assistant.presentation.events.GeminiChatSearchEvent
import com.gemini.assistant.presentation.events.GeminiSearchEvent
import com.gemini.assistant.presentation.states.GeminiSearchState
import com.gemini.assistant.utils.Constants.EXTERNAL_STORAGE_PERMISSION
import com.gemini.assistant.utils.Constants.MEDIA_IMAGES_PERMISSION
import com.gemini.assistant.utils.Constants.MEDIA_VISUAL_USER_SELECTED_PERMISSION
import com.gemini.assistant.utils.Constants._02_5f
import com.gemini.assistant.utils.Constants._05f
import com.gemini.assistant.utils.Constants._07_5f
import com.gemini.assistant.utils.Constants._07f
import com.gemini.assistant.utils.Constants._16sp
import com.gemini.assistant.utils.Constants._18sp
import com.gemini.assistant.utils.Constants._300L
import com.gemini.assistant.utils.Constants._500
import com.gemini.assistant.utils.GeminiResult
import com.gemini.assistant.utils.helpers.compressBitmap
import com.gemini.assistant.utils.helpers.isKeyboardOpen
import com.gemini.assistant.utils.helpers.parseUriToBitmap
import com.gemini.assistant.utils.helpers.permission.ExternalStoragePermissionProvider
import com.gemini.assistant.utils.helpers.permission.MediaImagesPermissionProvider
import com.gemini.assistant.utils.helpers.permission.MediaVisualUserSelectedPermissionProvider
import com.gemini.assistant.utils.helpers.permission.goToAppSettings
import com.gemini.assistant.utils.helpers.permission.isShouldShowRequestPermissionRationale
import com.gemini.assistant.utils.helpers.permission.sdkVersionHandler
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
    val searchRequest = searchRequestModel.searchRequest
    val searchRequestImages = searchRequestModel.searchRequestImages
    val searchResponseModel = geminiSearchState.searchResponseModel
    val expandedImage = geminiSearchState.expandedImage
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

    val clipboardManager = LocalClipboardManager.current

    val isLoading = searchResponseModel is GeminiResult.Loading

    val isConnectionAvailable = connectivityStatus is ConnectivityStatus.Available

    val columnMaxHeight = scrollState.maxValue

    val deviceSize = Modifier.fillMaxSize()

    LaunchedEffect(key1 = isKeyboardOpen) {
        if (!isKeyboardOpen && isSearchTextFieldFocused) focusManager.clearFocus()
    }

    LaunchedEffect(key1 = scrollState.canScrollForward) {
        val isFloatingActionButtonShow = scrollState.canScrollForward && !isLoading
        snapshotFlow { isFloatingActionButtonShow }
            .distinctUntilChanged()
            .debounce(_300L)
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
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
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
        modifier = deviceSize
            .background(MaterialTheme.colorScheme.background),
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .weight(_07_5f)
                .padding(
                    start = dimensionResource(id = R.dimen._10dp),
                    end = dimensionResource(id = R.dimen._10dp),
                    bottom = dimensionResource(id = R.dimen._10dp)
                )
                .verticalScroll(scrollState),
            floatingActionButton = {
                AnimatedVisibility(
                    visible = isShowScrollDownButton,
                    enter = slideInVertically(
                        animationSpec = tween(durationMillis = _500),
                        initialOffsetY = { columnMaxHeight }),
                    exit = slideOutVertically(
                        animationSpec = tween(durationMillis = _500),
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
                    transitionSpec = {
                        (fadeIn(animationSpec = tween(durationMillis = _500))).togetherWith(
                            fadeOut(animationSpec = tween(durationMillis = _500))
                        )
                    },
                    label = ""
                ) { geminiResponse ->
                    when(geminiResponse) {
                        is GeminiResult.Error -> ErrorSection(
                            modifier = deviceSize
                                .padding(dimensionResource(id = R.dimen._5dp)),
                            errorMessage = geminiResponse.errorMessage.toString(),
                            onRetryButtonClick = {
                                onGeminiSearchEvent(GeminiSearchEvent.OnSearchRequest)
                            }
                        )
                        is GeminiResult.Loading -> LoadingSection(
                            modifier = deviceSize
                        )
                        is GeminiResult.Success -> SearchResponseSection(
                            modifier = deviceSize,
                            searchResponseModel = geminiResponse.data ?: SearchResponseModel(),
                            onTextCopied = { copiedText ->
                                val annotatedString = AnnotatedString(text = copiedText)
                                clipboardManager.setText(annotatedString)
                            }
                        )
                        is GeminiResult.Undefined -> WelcomeWidget(
                            modifier = deviceSize,
                            imageVector = Icons.Default.QuestionMark,
                            text = stringResource(id = R.string.search_welcome)
                        )
                    }
                }
            }
        }
        CustomDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen._10dp)),
            thickness = dimensionResource(id = R.dimen._3dp)
        )

        if (searchRequestImages.isNotEmpty()) {
            SearchRequestImageLazyRow(
                modifier = Modifier.fillMaxWidth(),
                images = searchRequestImages,
                onDeleteIconClick = { image ->
                    onGeminiSearchEvent(GeminiSearchEvent.OnSearchRequestImageDelete(image))
                },
                onExpandIconClick = { image ->
                    val uncompressedBitmap = image.compressBitmap(format = Bitmap.CompressFormat.JPEG, quality = 100)
                    uncompressedBitmap?.let {
                        onGeminiSearchEvent(GeminiSearchEvent.OnSearchRequestImageExpand(it))
                    }
                }
            )
        }

        ContentSurface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(_02_5f)
                .padding(dimensionResource(id = R.dimen._10dp))
        ) {
            Column {
                SearchTextField(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen._10dp),
                            top = dimensionResource(id = R.dimen._10dp),
                            end = dimensionResource(id = R.dimen._10dp),
                            bottom = dimensionResource(id = R.dimen._2dp)
                        )
                        .fillMaxWidth()
                        .weight(_07f)
                        .onFocusChanged { focusState ->
                            isSearchTextFieldFocused = focusState.isFocused
                        },
                    searchText = searchRequest,
                    onSearchTextChange = {
                        onGeminiSearchEvent(GeminiSearchEvent.OnSearchRequestTextUpdate(it))
                    },
                    textFieldEnabled = !isLoading && isConnectionAvailable,
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontSize = _18sp,
                        fontWeight = FontWeight.Bold
                    ),
                    placeHolderTextStyle = MaterialTheme.typography.labelSmall.copy(
                        fontSize = _16sp,
                        fontWeight = FontWeight.Bold
                    ),
                    trailingIconEnabled = searchRequest.isNotBlank(),
                    onSearchTrailingIconClick = {
                        onGeminiSearchEvent(GeminiSearchEvent.OnSearchRequest)
                    },
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen._25dp)),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                        focusedTextColor = MaterialTheme.colorScheme.onTertiary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onTertiary,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = _05f),
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = _05f),
                        focusedTrailingIconColor = MaterialTheme.colorScheme.tertiaryContainer,
                        unfocusedTrailingIconColor = MaterialTheme.colorScheme.tertiaryContainer,
                    )
                )
                ImagePickerIcon(
                    isIconEnabled = !isLoading,
                    onIconClick = {
                        sdkVersionHandler(
                            upsideDownCakeCase = {
                                multiplePermissionsGalleryLauncher.launch(arrayOf(MEDIA_IMAGES_PERMISSION, MEDIA_VISUAL_USER_SELECTED_PERMISSION))
                            },
                            tiramisuCase = {
                                multiplePermissionsGalleryLauncher.launch(arrayOf(MEDIA_IMAGES_PERMISSION))
                            },
                            olderVersionsCase = {
                                multiplePermissionsGalleryLauncher.launch(arrayOf(EXTERNAL_STORAGE_PERMISSION))
                            }
                        )
                    }
                )
                ConnectionInfoWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen._15dp),
                            end = dimensionResource(id = R.dimen._15dp),
                            bottom = dimensionResource(id = R.dimen._5dp)
                        ),
                    connectivityStatus = connectivityStatus
                )
            }
        }
    }
}