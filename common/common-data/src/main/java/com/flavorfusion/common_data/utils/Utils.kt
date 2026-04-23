package com.flavorfusion.common_data.utils

import kotlinx.serialization.json.JsonObject

object Utils {
    fun JsonObject?.getStringByKey(key: String): String? {
        return this?.get(key)?.toString()?.trim('"')
    }
}