package com.elyeproj.networkaccessevolution

interface NetworkAccess {
    fun fetchData(parameterName: String)
    fun terminate()
}