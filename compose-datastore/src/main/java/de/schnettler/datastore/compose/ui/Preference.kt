package de.schnettler.datastore.compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.schnettler.datastore.compose.LocalPreferenceEnabledStatus
import de.schnettler.datastore.compose.model.BasePreferenceItem.PreferenceItem

@ExperimentalMaterialApi
@Composable
internal fun Preference(
    item: PreferenceItem<*>,
    summary: String? = null,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = LocalPreferenceEnabledStatus.current && item.enabled
    StatusWrapper(enabled = isEnabled) {
        ListItem(
            text = {
                Text(
                    text = item.title,
                    maxLines = if (item.singleLineTitle) 1 else Int.MAX_VALUE
                )
            },
            secondaryText = { Text(text = summary ?: item.summary) },
            icon = {
                Icon(
                    imageVector = item.icon,
                    null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                )
            },
            modifier = Modifier.clickable(onClick = { if (isEnabled) onClick() }),
            trailing = trailing,
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun Preference(
    item: PreferenceItem<*>,
    summary: @Composable () -> Unit,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = LocalPreferenceEnabledStatus.current && item.enabled
    StatusWrapper(enabled = isEnabled) {
        ListItem(
            text = {
                Text(
                    text = item.title,
                    maxLines = if (item.singleLineTitle) 1 else Int.MAX_VALUE
                )
            },
            secondaryText = summary,
            icon = {
                Icon(
                    imageVector = item.icon,
                    null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                )
            },
            modifier = Modifier.clickable(onClick = { if (isEnabled) onClick() }),
            trailing = trailing,
        )
    }
}

@Composable
fun StatusWrapper(enabled: Boolean = true, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalContentAlpha provides if (enabled) ContentAlpha.high else ContentAlpha.disabled) {
        content()
    }
}