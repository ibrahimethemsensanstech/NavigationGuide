package com.ibrahimethemsen.navigationguide.ext

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.serializer

inline fun <reified T> createRoutePattern(): String =
    createRoutePattern(serializer<T>())

@OptIn(ExperimentalSerializationApi::class)
fun <T > createRoutePattern(serializer: KSerializer<T>): String {
    val destination = createRouteSlug(serializer)
    if (serializer.descriptor.elementsCount == 0) {
        return destination
    }

    val path = StringBuilder()
    val query = StringBuilder()
    for (i in 0 until serializer.descriptor.elementsCount) {
        val name = serializer.descriptor.getElementName(i)
        if (serializer.descriptor.isNavTypeOptional(i)) {
            query.append("&$name={$name}")
        } else {
            path.append("/{$name}")
        }
    }
    if (query.isNotEmpty()) {
        query[0] = '?'
    }

    return destination + path.toString() + query.toString()
}
@OptIn(ExperimentalSerializationApi::class)
internal fun SerialDescriptor.isNavTypeOptional(index: Int): Boolean =
    isElementOptional(index) ||
            getElementDescriptor(index).let {
                it.isNullable || it.kind == PrimitiveKind.STRING
            }

@OptIn(ExperimentalSerializationApi::class)
internal fun createRouteSlug(serializer: KSerializer<*>): String =
    serializer.descriptor.serialName