package com.ticket

import org.junit.Test



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val num = 123456
        val firstHalf: Int = num / 100000 + num / 10000 % 10 + num / 1000 % 100
        val secondHalf: Int = num % 10 + num / 10 % 10 + num / 100 % 10
        println(secondHalf)
    }
}
