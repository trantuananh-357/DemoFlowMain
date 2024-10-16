package com.example.democoldflow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.runBlocking

fun main() {
    demoToList()

    demoToSet()

    demoReduce()

    demoFold()
}

private fun demoFold() {
    runBlocking {
        val flow = (1..10).asFlow()

        val result = flow.fold(1) { accumulator, value ->
            accumulator + value
        }

        println("fold: $result")
    }
}

private fun demoReduce() {
    runBlocking {
        val flow = (1..10).asFlow()
        val result = flow.reduce { accumulator, value ->
            println("accumulator: $accumulator, value: $value")
            accumulator + value
        }
        println("reduce: $result")
    }
}

private fun demoToSet() {
    runBlocking {
        val flowTerminal = flowOf(1, 2, 2, 2, 3, 4, 5, 6, 7)
        val result = flowTerminal.toSet()
        println("toSet: $result")
    }
}

private fun demoToList() {
    runBlocking {
        val flowTerminal = (1..10).asFlow()
        val result = flowTerminal.toList()
        println("toList: $result")
    }
}