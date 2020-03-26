package com.ticket.utils

object Constants {
    object Api{
        const val BASE_URL = "https://ticket-14620.firebaseio.com/"
        const val JSON_REQUEST_TYPE = "application/json; utf-8"
    }

    object Delays {
        const val GAME_DELAY: Long = 30000
        const val SPLASH_TIME_DELAY: Long = 3000
        const val TIME_DELAY: Long = 1000
    }

    object Timer {
        const val TIMER_TIME_IN_MILLISECONDS: Long = 4000
        const val MILLISECONDS_IN_SECONDS: Long = 1000
    }

    object Denominators {
        const val TEN: Int = 10
        const val HUNDRED: Int = 100
        const val THOUSAND: Int = 1000
        const val TEN_THOUSAND: Int = 10000
        const val HUNDRED_THOUSAND: Int = 100000
    }

    object Others {
        const val EXTRA_NAME = "name"
        const val EXTRA_BACK = "back"
    }
}