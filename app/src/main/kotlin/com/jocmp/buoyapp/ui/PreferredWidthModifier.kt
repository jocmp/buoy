package com.jocmp.buoyapp.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Stable
fun Modifier.widthMaxSingleColumn() = then(Modifier.widthIn(max = 450.dp))

@Composable
fun Modifier.preferredMaxWidth() = then(
    if (isCompact()) {
        Modifier.fillMaxWidth()
    } else {
        Modifier.widthIn(max = 600.dp)
    }
)
