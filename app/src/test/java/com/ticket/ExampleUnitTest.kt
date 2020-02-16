package com.ticket

import com.ticket.entity.User
import org.junit.Test


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
}
