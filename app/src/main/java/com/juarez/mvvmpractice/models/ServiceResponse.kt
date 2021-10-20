package com.juarez.mvvmpractice.models

data class ServiceResponse<T>(val result: T?, val error: String?)