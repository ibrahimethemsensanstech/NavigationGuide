package com.ibrahimethemsen.navigationguide.ext

fun String.replacePackage(): String {
    return this.replace(
        "com.ibrahimethemsen.navigationguide.",
        ""
    )
}