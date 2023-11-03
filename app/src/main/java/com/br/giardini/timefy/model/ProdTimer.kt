package com.br.giardini.timefy.model

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class ProdTimer (
    var nome:String,
    var grupo:String?,
    val lastSession: Duration?=null,
    val sessionHistory: List<Duration>?=null,
    var active:Boolean=false

)

fun ProdTimer.TotalTime():Duration {
    var total=0.seconds
    this.sessionHistory?.let {
        total=it.reduce{acc,value -> acc+ value}
    }
    return total
}