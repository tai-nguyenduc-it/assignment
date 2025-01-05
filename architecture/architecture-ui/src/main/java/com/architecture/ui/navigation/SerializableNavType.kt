package com.architecture.ui.navigation

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

const val DEFAULT_CHARSET = "UTF-8"

inline fun <reified T : Any> serializableType(isNullableAllowed: Boolean = false, json: Json = Json) =
    object : NavType<T>(isNullableAllowed = isNullableAllowed) {
        override fun get(bundle: Bundle, key: String) = bundle.getString(key)?.let<String, T> { value ->
            json.decodeFromString(URLDecoder.decode(value, DEFAULT_CHARSET))
        }

        override fun parseValue(value: String): T = json.decodeFromString(URLDecoder.decode(value, DEFAULT_CHARSET))

        override fun serializeAsValue(value: T): String = URLEncoder.encode(json.encodeToString(value), DEFAULT_CHARSET)

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putString(key, URLEncoder.encode(json.encodeToString(value), DEFAULT_CHARSET))
        }
    }
