package com.example.democoldflow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    demoDebounce()
}


@OptIn(FlowPreview::class)
fun demoDebounce() {
    runBlocking {
        flow<Float> {
            emit(1f)
            delay(100)
            emit(2f)
            delay(200)
            emit(2.5f)
            delay(100)
            emit(3f)
        }
            .debounce(200)
            .collect { value ->
                println("Collected: $value")
            }
    }
}

