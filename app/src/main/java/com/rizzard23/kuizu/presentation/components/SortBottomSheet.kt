package com.rizzard23.kuizu.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortModalSheet(
    bottomSheetState : SheetState,
    onDismissRequest : () -> Unit,
) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    var sortDirectionUp by rememberSaveable { mutableStateOf(true) }
    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.heightIn(min=256.dp)
    ) {
        SortModalSheetContent(
            onSortItemClick = {
                if(selectedIndex == it) {
                    sortDirectionUp = !sortDirectionUp
                }
                selectedIndex = it
            },
            selectedIndex = selectedIndex,
            sortDirectionUp = sortDirectionUp,
        )
    }
}

@Composable
fun SortModalSheetContent(
    onSortItemClick: (Int) -> Unit,
    selectedIndex: Int,
    sortDirectionUp: Boolean,
) {
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text("Sort",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        SortQuizzesType.entries.forEachIndexed { index,entry ->
            ListItem(
                headlineContent = {
                    Text(text = entry.displayName)
                },
                trailingContent = {
                    if(index == selectedIndex) {
                        val icon = if(sortDirectionUp) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.Refresh
                        }

                        Icon(icon, "Favorite")
                    }
                },
                modifier = Modifier.clickable {onSortItemClick(index)}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview( )
@Composable
private fun SortBottomSheetPreview() {
    Surface {
        SortModalSheetContent(
            onSortItemClick = {},
            selectedIndex = 1,
            sortDirectionUp = true,
        )
    }
}

sealed class SortQuizzesType(
    val displayName : String,
) {
    companion object {
        val entries = listOf(
            Alphabetically,
            DateAdded,
            DateCreated,
        )
    }
    data object Alphabetically : SortQuizzesType("Alphabetically")
    data object DateAdded : SortQuizzesType("Date added")
    data object DateCreated : SortQuizzesType("Date created")
}