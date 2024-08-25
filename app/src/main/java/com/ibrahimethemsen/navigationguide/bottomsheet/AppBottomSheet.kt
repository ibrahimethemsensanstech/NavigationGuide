package com.ibrahimethemsen.navigationguide.bottomsheet

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    showBottomSheet: Boolean,
    bottomSheetValueChange: (Boolean) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                bottomSheetValueChange(false)
            },
            sheetState = sheetState
        ) {
            Button(
                //Bottom Padding -> modifier = Modifier.navigationBarsPadding(),
                onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            bottomSheetValueChange(false)
                        }
                    }
                }) {
                Text("Navigation Bottom Sheet")
            }
        }
    }
}