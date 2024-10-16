package com.example.democoldflow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking


fun main(){
    demoFlatMapConcat()

    demoFlatMapMerge()

    demoFlatLatest()
}

@OptIn(FlowPreview::class)
fun demoFlatMapConcat() {
    runBlocking {
        val flowFlatten = flowOf(1, 2, 3, 4)
        flowFlatten.flatMapConcat { value ->
            flow {
                emit("$value: First emit")
                delay(500)
                emit("$value: Second emit")
            }
        }.collect { value ->
            println("flatMapConcat: $value")
        }
    }

}

@OptIn(ExperimentalCoroutinesApi::class)
private fun demoFlatLatest() {
    runBlocking {
        val flowFlatten = flowOf(1, 2, 3, 4)
        flowFlatten.flatMapLatest { value ->
            flow {
                emit("$value: First emit")
                delay(500)
                emit("$value: Second emit")
            }
        }.collect { value ->
            println("flatMapLatest: $value")
        }
    }
}

@OptIn(FlowPreview::class)
private fun demoFlatMapMerge() {
    runBlocking {
        val flowFlatten = flowOf(1, 2, 3, 4)
        flowFlatten.flatMapMerge { value ->
            flow {
                emit("$value: First emit")
                delay(500)
                emit("$value: Second emit")
            }
        }.collect { value ->
            println("flatMapMerge: $value")
        }
    }
}

