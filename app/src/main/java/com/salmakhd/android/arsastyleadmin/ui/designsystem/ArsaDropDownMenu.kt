package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties

data class ArsaDropDownMenuState <T>(
    val identifier: T,
    val text: String
)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <T> ArsaDropDownMenu(
    menuItems: List<ArsaDropDownMenuState<T>>,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    menuExpanded: Boolean = remember { false },
    onDismiss: () -> Unit = {},
    selectedItemBox: @Composable () -> Unit = {},
) {
    var mOptionsBoxSize by remember { mutableStateOf(Size.Zero) }

    // define drop down menu
    Column(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                // This value is used to assign to
                // the DropDown the same width
                mOptionsBoxSize = coordinates.size.toSize()
            }
    ) {
        selectedItemBox()
        // Create a drop-down menu with list of items,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { onDismiss() },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary, shape = MaterialTheme.shapes.medium)
                .width(with(LocalDensity.current) { mOptionsBoxSize.width.toDp() })
                .clip(MaterialTheme.shapes.medium),
            properties = PopupProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            menuItems.forEach { item ->
                DropdownMenuItem(onClick = {
                    onItemSelected(item.identifier)
                },text =  {
                    Text(text = item.text, style = MaterialTheme.typography.titleMedium)
                })
            }
        }
    }
}

@SmallDevicePreview
@Composable
fun ArsaDropDownMenuPreview() {
    ArsaDropDownMenu(menuItems = listOf(
        ArsaDropDownMenuState(
            identifier = "",
            text = "",
        ),
        ArsaDropDownMenuState(
            identifier = "2",
            text = "fdf"
        )
    ),
        onItemSelected = {}
    )
}