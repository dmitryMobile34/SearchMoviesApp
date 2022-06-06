package com.example.filmplotonfragmentstest.interfaces

interface ResultListener<S> {
    fun onSuccess(successModel: S)
    fun onFail(any: String?)
    fun onError(e: Throwable?)
}