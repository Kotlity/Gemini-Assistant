package com.gemini.assistant.utils.helpers.permission

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Storage
import androidx.compose.ui.graphics.vector.ImageVector
import com.gemini.assistant.R

interface PermissionProvider {
    fun permissionDescription(context: Context, isPermissionPermanentlyDeclined: Boolean): String
    fun permissionIcon(): ImageVector = Icons.Default.Image
}

class MediaVisualUserSelectedPermissionProvider: PermissionProvider {

    override fun permissionDescription(context: Context, isPermissionPermanentlyDeclined: Boolean): String {
        return if (isPermissionPermanentlyDeclined) context.getString(R.string.media_visual_user_selected_permission_permanently_declined)
        else context.getString(R.string.gallery_rationale_permission)
    }
}

class MediaImagesPermissionProvider: PermissionProvider {

    override fun permissionDescription(context: Context, isPermissionPermanentlyDeclined: Boolean): String {
        return if (isPermissionPermanentlyDeclined) context.getString(R.string.media_images_permission_permanently_declined)
        else context.getString(R.string.gallery_rationale_permission)
    }
}

class ExternalStoragePermissionProvider: PermissionProvider {

    override fun permissionDescription(context: Context, isPermissionPermanentlyDeclined: Boolean): String {
        return if (isPermissionPermanentlyDeclined) context.getString(R.string.external_storage_permission_permanently_declined)
        else context.getString(R.string.external_storage_rationale_permission)
    }

    override fun permissionIcon(): ImageVector {
        return Icons.Default.Storage
    }
}