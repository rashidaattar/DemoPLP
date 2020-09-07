package com.demo.demoplp.base.utils

import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Created by Rashida on 9/7/20.
 *
 */

class ResponseHelper {
    companion object{

        const val NO_NETWORK = 0
        const val TIME_OUT = 1
        const val UNKNOWN_HOST = 2
        const val OTHER = 3

        /**
         * to get the type of throwable (network, time_out, ..)
         *
         * @param throwable
         * @return the throwable type
         */
        fun getErrorState(throwable: Throwable?): Int {
            Timber.d("got error"+throwable?.localizedMessage)
            if (throwable is SocketTimeoutException) {
                return TIME_OUT
            } else if (throwable is UnknownHostException) {
                return UNKNOWN_HOST
            }
            return if (throwable is IOException) {
                NO_NETWORK
            } else {
                OTHER
            }
        }
    }

}
