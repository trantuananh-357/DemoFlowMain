package com.example.democoldflow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

fun main(){
    demoCompleteException()
}

fun demoCompleteException() {
    runBlocking {
        val flowCompleteException = flow {
            emit(1)
            emit(2)
            throw RuntimeException("Lỗi đây nè!")
            emit(3)
        }
            .catch { e ->
                println("Bắt được nè: ${e.message}")
            }
            .onCompletion { cause ->
                if (cause == null) {
                    println("Hoàn thành flow nè")
                } else {
                    println("Hoàn thành nhưng mà lỗi nè: $cause")
                }
            }

        flowCompleteException.collect { value ->
            println("Collected: $value")
        }
    }
}