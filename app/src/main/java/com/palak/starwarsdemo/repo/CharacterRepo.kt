package com.palak.starwarsdemo.repo

import androidx.paging.*
import com.palak.starwarsdemo.utils.Constants
import com.palak.starwarsdemo.api.ApiInterface
import com.palak.starwarsdemo.home.paging.CharacterSource
import javax.inject.Inject

class CharacterRepo @Inject constructor(private val apiInterface: ApiInterface) {

    fun getCharsList() =
        Pager(config = PagingConfig(pageSize = Constants.CHARACTER_PAGE_SIZE),
            pagingSourceFactory = {
                CharacterSource(apiInterface)
            }).liveData
}