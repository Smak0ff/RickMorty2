package com.example.rickmorty2.model.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.rickmorty2.model.Results
import com.example.rickmorty2.retrofit.RetrofitInterface
import com.example.rickmorty2.ui.pagedList.CharactersDataSource

class CharactersRepository(private val api: RetrofitInterface): DataSource.Factory<Int, Results>() {
    val charactersDataSourceLiveData = MutableLiveData<CharactersDataSource>()
    override fun create(): DataSource<Int, Results> {
        val charactersDataSource = CharactersDataSource(api)
        charactersDataSourceLiveData.postValue(charactersDataSource)
        return charactersDataSource
    }
}