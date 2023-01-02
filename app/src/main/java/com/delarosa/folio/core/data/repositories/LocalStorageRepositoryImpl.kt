package com.delarosa.folio.core.data.repositories

import com.delarosa.folio.core.data.datasource.LocalStorageDataSource
import com.delarosa.folio.core.repositories.LocalStorageRepository

class LocalStorageRepositoryImpl(private val localStorageDataSource: LocalStorageDataSource) : LocalStorageRepository {

    override fun getStringValue(key: String): String? =
        localStorageDataSource.getData(key, String::class.java) as? String

    override fun getBooleanValue(key: String, default: Boolean?): Boolean =
        localStorageDataSource.getData(key, Boolean::class.java, default) as Boolean

    override fun getLongValue(key: String): Long = localStorageDataSource.getData(key, Long::class.java) as Long

    override fun getFloatValue(key: String): Float = localStorageDataSource.getData(key, Float::class.java) as Float

    override fun getIntValue(key: String): Int = localStorageDataSource.getData(key, Int::class.java) as Int

    override fun save(key: String, value: String) { localStorageDataSource.saveData(key, value) }

    override fun save(key: String, value: Long) { localStorageDataSource.saveData(key, value) }

    override fun save(key: String, value: Float) { localStorageDataSource.saveData(key, value) }

    override fun save(key: String, value: Int) { localStorageDataSource.saveData(key, value) }

    override fun save(key: String, value: Boolean) { localStorageDataSource.saveData(key, value) }

    override fun delete(key: String) { localStorageDataSource.remove(key) }

    override fun deleteAll() { localStorageDataSource.clear() }
}
