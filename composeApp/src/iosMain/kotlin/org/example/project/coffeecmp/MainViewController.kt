package org.example.project.coffeecmp

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.coffeecmp.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}