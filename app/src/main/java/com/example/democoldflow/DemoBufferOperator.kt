package com.example.democoldflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() {
    demoBuffer()

    demoConflate()

    demoCollectLastest()

    demoZip()

    demoCombine()
}

private fun demoCombine() {
    runBlocking {
        val flow1 = (1..3).asFlow().onEach { delay(100) }
        val flow2 = flowOf("A", "B", "C").onEach { delay(150) }

        flow1.combine(flow2) { number, letter ->
            "$number -> $letter"
        }
            .collect { value ->
                println(value)
            }
    }
}

private fun demoZip() {
    runBlocking {
        val flow1 = (1..3).asFlow().onEach { delay(500) }
        val flow2 = flowOf("A", "B", "C").onEach { delay(1000) }

        flow1.zip(flow2) { number, letter ->
            "$number -> $letter"
        }.collect { value ->
            println(value)
        }
    }
}

private fun demoCollectLastest() {
    runBlocking {
        val flow = flow {
            for (i in 1..3) {
                delay(100)
                emit(i)
            }
        }

        flow.collectLatest { value ->
            println("Collecting $value")
            delay(200)
            println("Done collecting $value")
        }
    }
}

private fun demoConflate() {
    runBlocking {
        val flow = flow {
            for (i in 1..3) {
                println("Emitting $i")
                delay(3000)
                emit(i)
            }
        }

        flow.collect { value ->
            delay(3000)
            println("Collected: $value")
        }

        runBlocking {
            val flow = flow {
                for (i in 1..5) {
                    delay(1000)
                    emit(i)
                }
            }.conflate() // Chỉ thu thập giá trị mới nhất nếu phát nhanh hơn thu thập

            flow.collect { value ->
                delay(500)
                println("Collected: $value")
            }
        }
    }
}

private fun demoBuffer() {
    runBlocking {
        val flow = flow {
            for (i in 1..3) {
                println("Emitting $i")
                delay(3000)
                emit(i)
            }
        }.buffer()

        flow.collect { value ->
            delay(300)
            println("Collected: $value")
        }
    }
}