package com.example.robottest

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun Home() {
    Text(
        text = stringResource(id = R.string.home_description),
        modifier = Modifier.semantics { contentDescription = "Home" })
}