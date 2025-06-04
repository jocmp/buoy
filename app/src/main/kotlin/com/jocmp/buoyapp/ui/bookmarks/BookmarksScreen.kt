package com.jocmp.buoyapp.ui.bookmarks

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookmarksScreen(viewModel: BookmarksViewModel = koinViewModel()) {
    val username by viewModel.username.collectAsStateWithLifecycle("")

    Text(username)
}
