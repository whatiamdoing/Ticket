package com.ticket

import com.ticket.entity.User
import org.junit.Test
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val num = 132123
        val firstHalf: Int = num / 100000 + num / 10000 % 10 + num / 1000 % 10
        val secondHalf: Int = num % 10 + num / 10 % 10 + num / 100 % 10
        println(secondHalf)
    }

    @Test
    fun mapTest() {
        val map = mapOf(
            "Dima" to 2,
            "Ivan" to 3,
            "David" to 44
        )
        val listOfUsers = map.map {
            (User(it.key, it.value))
        }.sortedByDescending{it.record}
        println(listOfUsers)
    }

    @Test
    fun getLuckyTicket() {
        val d1 = Random.nextInt(0,9)
        val d2 = Random.nextInt(0,9)
        val d3 = Random.nextInt(0,9)
        val sum = d1 + d2 + d3
        val d4 = Random.nextInt(max(0, sum - 18), min(9 + 1, sum))
        val d5 = Random.nextInt(max(0, sum - d4 - 9), min(9 + 1, sum - d4))
        val d6 = sum - d4 - d5
        println("" + d1 + d2 + d3 + d4 +d5 + d6)
    }
}
