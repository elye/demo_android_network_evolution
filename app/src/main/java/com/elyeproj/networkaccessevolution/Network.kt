package com.elyeproj.networkaccessevolution

import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object Network {
    private val httpClient = OkHttpClient.Builder().build()

    private val httpUrlBuilder = HttpUrl.Builder()
            .scheme("https")
            .host("en.wikipedia.org")
            .addPathSegment("w")
            .addPathSegment("api.php")
            .addQueryParameter("action", "query")
            .addQueryParameter("format", "json")
            .addQueryParameter("list", "search")

    private const val SEARCH_KEY = "srsearch"

    @JvmStatic fun fetchHttp(queryString : String) : String {
        val httpUrl = httpUrlBuilder
                .removeAllQueryParameters(SEARCH_KEY)
                .addQueryParameter(SEARCH_KEY, queryString)
                .build()
        val request = Request.Builder().get().url(httpUrl).build()
        val response = httpClient.newCall(request).execute()
        val raw = response.body?.string()
        val result = Gson().fromJson(raw, Model.Result::class.java)
        return result.query.searchinfo.totalhits.toString()
    }
}