package com.storium

import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val name: String = "iOS ${UIDevice.currentDevice.systemVersion}"
}
