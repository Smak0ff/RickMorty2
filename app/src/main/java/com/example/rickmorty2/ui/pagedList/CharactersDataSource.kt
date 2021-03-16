package com.example.rickmorty2.ui.pagedList

import androidx.paging.PageKeyedDataSource
import com.example.rickmorty2.model.Character
import com.example.rickmorty2.model.Results
import com.example.rickmorty2.retrofit.RetrofitInterface
import com.example.rickmortyproject.utils.FIRST_PAGE
import retrofit2.Call
import retrofit2.Response

class CharactersDataSource(private val api: RetrofitInterface): PageKeyedDataSource<Int, Results>() {
    private lateinit var call: Call<Character>

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Results>
    ) {
        call = api.getAllCharacter(FIRST_PAGE)
        call.enqueue(object : retrofit2.Callback<Character>{
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful){
                    val apiResponse = response.body()
                    apiResponse.let {
                        if (apiResponse != null) {
                            callback.onResult(apiResponse.results, null, FIRST_PAGE + 1)
                        }
                        else{
                            println(response.errorBody())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                println(t.message)
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {
        call = api.getAllCharacter(params.key)
        call.enqueue(object : retrofit2.Callback<Character> {
            override fun onResponse(
                call: Call<Character>,
                response: retrofit2.Response<Character>
            ) {
                if (response.isSuccessful) {

                    val apiResponse = response.body()
                    val key = if (params.key > 2) params.key - 1 else 1
                    apiResponse.let {
                        if (apiResponse != null) {
                            callback.onResult(apiResponse.results, key)
                        } else {
                            println(response.errorBody())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                println(t.message)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {
        call = api.getAllCharacter(params.key)
        call.enqueue(object : retrofit2.Callback<Character> {
            override fun onResponse(
                call: Call<Character>,
                response: retrofit2.Response<Character>
            ) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val key = params.key + 1
                    apiResponse.let {
                        if (apiResponse != null) {
                            callback.onResult(apiResponse.results, key)
                        } else {
                            println(response.errorBody())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                println(t.message)
            }
        })
    }
}