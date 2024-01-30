package tech.annexflow.textfieldselectionbug

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

@Suppress("UNUSED")
fun MainViewController(): UIViewController = ComposeUIViewController {
    Sample()
}