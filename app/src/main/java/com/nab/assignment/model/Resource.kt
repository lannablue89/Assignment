package com.nab.assignment.model

/**
 * A generic class that holds a value with its loading status.
 */
data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(err: Throwable, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, err)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun Resource<*>.isSuccess() = status == Status.SUCCESS
        
        fun Resource<*>.isError() = status == Status.ERROR
    }

}