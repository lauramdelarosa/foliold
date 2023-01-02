package com.delarosa.folio.core.repositories

interface LocalStorageRepository {

    fun getStringValue(key: String): String?

    fun getLongValue(key: String): Long

    fun getFloatValue(key: String): Float

    fun getIntValue(key: String): Int

    fun getBooleanValue(key: String, default: Boolean? = false): Boolean

    fun save(key: String, value: String)

    fun save(key: String, value: Long)

    fun save(key: String, value: Float)

    fun save(key: String, value: Int)

    fun save(key: String, value: Boolean)

    fun delete(key: String)

    fun deleteAll()
}
