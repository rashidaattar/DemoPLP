package com.demo.demoplp.base.utils

import io.reactivex.Scheduler


/**
 * Created by Rashida on 9/7/20.
 *
 */

 interface SchedulerProvider {
    fun io(): Scheduler?
    fun ui(): Scheduler?
    fun computation(): Scheduler?
    fun triggerActions()
}
