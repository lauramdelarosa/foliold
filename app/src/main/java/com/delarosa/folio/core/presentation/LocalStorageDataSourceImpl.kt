package com.delarosa.folio.core.presentation

import android.content.SharedPreferences
import com.delarosa.folio.core.data.datasource.LocalStorageDataSource
import com.google.gson.Gson

class LocalStorageDataSourceImpl(private val sharedPreferences: SharedPreferences) : LocalStorageDataSource {

    override fun saveData(key: String, value: Any) {
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            else -> editor.putString(key, Gson().toJson(value))
        }
        editor.apply()
    }

    override fun getData(key: String, type: Class<*>, default: Boolean?): Any? =
        when (type) {
            String::class.java -> sharedPreferences.getString(key, null)
            Int::class.java -> sharedPreferences.getInt(key, ZERO)
            Boolean::class.java -> sharedPreferences.getBoolean(key, default ?: PREFERENCE_BOOLEAN_DEFAULT_VALUE)
            Float::class.java -> sharedPreferences.getFloat(key, PREFERENCE_FLOAT_DEFAULT_VALUE)
            Long::class.java -> sharedPreferences.getLong(key, PREFERENCE_LONG_DEFAULT_VALUE)
            else -> Gson().fromJson(sharedPreferences.getString(key, null), type)
        }

    override fun remove(key: String) {
        if (sharedPreferences.contains(key)) {
            sharedPreferences.edit().remove(key).apply()
        }
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}

private const val PREFERENCE_LONG_DEFAULT_VALUE = 0.toLong()
private const val PREFERENCE_FLOAT_DEFAULT_VALUE = 0.toFloat()
private const val PREFERENCE_BOOLEAN_DEFAULT_VALUE = false
private const val ZERO = 0
