package com.example.democoldflow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.runBlocking


fun main(){
    demoOnEach()
    demoTakeWhile()
    demoDropWhile()
}

fun demoOnEach(){
    runBlocking {
        val flow = (1..5).asFlow()

        flow.onEach { value ->
            println("Processing: $value")
        }
            .collect { value ->
                println("Collected: $value")
            }
    }
}

fun demoTakeWhile(){
    runBlocking {
        val flow = (1..10).asFlow()

        flow.takeWhile { it <= 5 }
            .collect { value ->
                println("Collected: $value")
            }
    }
}

fun demoDropWhile(){
    runBlocking {
        val flow = (1..10).asFlow()

        flow.dropWhile { it <= 5 }
            .collect { value ->
                println("Collected: $value")
            }
    }
}