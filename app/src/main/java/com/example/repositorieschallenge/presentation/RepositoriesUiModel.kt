package com.example.repositorieschallenge.presentation

import android.content.Context
import com.example.repositorieschallenge.domain.model.RepositoriesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

interface RepositoriesUiModel {
    fun getLocalRepositories(): List<RepositoriesModel>
    fun saveLocalRepositories(repository: List<RepositoriesModel>)
    fun removeLocalRepositories(repository: List<RepositoriesModel>)
}

class RepositoriesUiModelImpl(private val context: Context) : RepositoriesUiModel {

    override fun getLocalRepositories(): List<RepositoriesModel> {
        val sharedPref = context.getSharedPreferences(LOCAL_KEY, Context.MODE_PRIVATE)
        val gsonValue = sharedPref?.getString(LOCAL_KEY, null)
        if (gsonValue != null) {
            val itemType: Type = object : TypeToken<List<RepositoriesModel>>() {}.type
            return Gson().fromJson(gsonValue, itemType)
        }
        return ArrayList()
    }

    override fun saveLocalRepositories(repository: List<RepositoriesModel>) {
        val items = getLocalRepositories()
        if (items.isEmpty()) {
            saveList(repository)
        }
        return
    }

    override fun removeLocalRepositories(repository: List<RepositoriesModel>) {
        val repositories = getLocalRepositories()
        if (repositories.isNotEmpty())
            context.deleteSharedPreferences(LOCAL_KEY)
    }

    private fun saveList(favorites: List<RepositoriesModel>) {
        val sharedPref =
            context.getSharedPreferences(LOCAL_KEY, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            val json = Gson().toJson(favorites)
            putString(LOCAL_KEY, json)
            apply()
        }
    }

    companion object {
        const val LOCAL_KEY = "LOCAL_KEY"
    }
}