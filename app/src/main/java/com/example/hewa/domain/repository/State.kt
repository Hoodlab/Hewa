package com.example.hewa.domain.repository

/**
 *A discriminated union tha encapsulate encapsulates a successful State with a value of type T
 * or a failure with an error message.
 * contains different classes that can be used to represent success,Error or Loading
 */
sealed class State<T>(val data: T? = null, val message: String? = null) {
    class Loading<T> : State<T>()
    class Success<T>(data: T) : State<T>(data)
    class Error<T>(message: String) : State<T>(message = message)
}

