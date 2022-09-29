package com.palak.starwarsdemo.utils

object Constants {

    //API stuff
    const val BASE_URL = "https://swapi.dev/api/"

    const val GET_CHARACTERS = "${BASE_URL}people/"

    const val FIRST_PAGE_NO = 1
    val CHARACTER_PAGE_SIZE: Int = 1
    val PAGE_QUERY_PARAM: String = "page"

    val CHARACTER_CREATED_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
    val CHARACTER_CREATED_DATE_FORMAT_PRINT = "yyyy-MM-dd HH:mm"

}