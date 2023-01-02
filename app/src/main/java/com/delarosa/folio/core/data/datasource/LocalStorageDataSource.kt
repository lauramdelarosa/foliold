package com.delarosa.folio.core.data.datasource

interface LocalStorageDataSource {

    fun saveData(key: String, value: Any)

    fun getData(key: String, type: Class<*>, default: Boolean? = false): Any?

    fun remove(key: String)

    fun clear()
}
