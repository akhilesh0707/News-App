package com.aqube.newsapp.base.network

interface ResponseHandler<M : Model> {
    fun onRequestFailure(errorMessage: String?)

    fun onRequestSuccess(model: M)
}