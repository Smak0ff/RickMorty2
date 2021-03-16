package com.example.rickmorty2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.rickmorty2.model.Results
import com.example.rickmorty2.model.repository.CharactersRepository
import com.example.rickmorty2.ui.pagedList.CharactersDataSource
import com.example.rickmortyproject.utils.PAGE_SIZE

class MainFragmentViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {
    //Нам необходимо передать данные из CharactersDataSource, создадим для них хранилище LiveData
    private val charactersDataSourceLiveData: LiveData<CharactersDataSource>

    //Создаём LiveData для обновления PagedList
    val charactersPagedListLiveData: LiveData<PagedList<Results>>

    init {
        //получаем данные из репозитория
        charactersDataSourceLiveData = charactersRepository.charactersDataSourceLiveData
        //Создаем билдер для Paged List
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()
        //Передаём Paged List в LiveData
        charactersPagedListLiveData = LivePagedListBuilder(charactersRepository, config).build()
    }
}