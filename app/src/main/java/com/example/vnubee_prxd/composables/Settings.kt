package com.example.vnubee_prxd.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vnubee_prxd.datatypes.SettingsOptions

@Composable
fun Settings(
    options: SettingsOptions,
    onSettingsChanged: (SettingsOptions) -> Unit
) {
    Column(modifier = Modifier.padding(12.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Show Likes and Comments",
                modifier = Modifier.padding(12.dp)
            )

            Switch(
                checked = options.showLikes,
                onCheckedChange = {
                    onSettingsChanged(options.copy(showLikes = it))
                }
            )
        }
    }
}