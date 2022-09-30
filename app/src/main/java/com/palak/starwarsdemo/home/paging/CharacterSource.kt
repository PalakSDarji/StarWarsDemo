package com.palak.starwarsdemo.home.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.palak.starwarsdemo.api.ApiInterface
import com.palak.starwarsdemo.models.Character
import com.palak.starwarsdemo.utils.Constants
import javax.inject.Inject

/**
 * This class acts as source of character data, It loads data as it is required.
 */
class CharacterSource @Inject constructor(private val apiInterface: ApiInterface) :
    PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            var nextPageNo : Int? = params.key ?: Constants.FIRST_PAGE_NO

            val response = apiInterface.getCharacters(nextPageNo)

            response?.next?.let {
                nextPageNo = Uri.parse(it)?.getQueryParameter(Constants.PAGE_QUERY_PARAM)?.toInt()
                    ?: Constants.FIRST_PAGE_NO
            } ?: kotlin.run {
                nextPageNo = null
            }

            response?.results?.let { listOfChars ->
                LoadResult.Page(data = listOfChars, prevKey = null, nextKey = nextPageNo)
            } ?: kotlin.run {
                LoadResult.Error(Exception(Throwable()))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}