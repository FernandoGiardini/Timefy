package com.br.giardini.timefy.model

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class ProdTimer (
    var nome:String,
    var grupo:String,
    val lastSession: Duration?=null,
    val sessionHistory: List<Duration>?=null,

)

fun ProdTimer.TotalTime(timer:ProdTimer):Duration {
    var total=0.seconds
    timer.sessionHistory?.let {
        total=it.reduce{acc,value -> acc+ value}
    }
    return total
}