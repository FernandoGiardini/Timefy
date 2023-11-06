package com.br.giardini.timefy.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

data class ProdTimer (
    var nome:String,
    var grupo:String?,
    var lastSession: Duration?=null,
    val sessionHistory: List<Duration>?=null,
    var active:MutableState<Boolean> = mutableStateOf(true),
    @OptIn(ExperimentalTime::class)
    val firstTimeMark:TimeSource.Monotonic.ValueTimeMark = TimeSource.Monotonic.markNow()

)

fun ProdTimer.TotalTime():Duration {
    var total=0.seconds
    this.sessionHistory?.let {
        total=it.reduce{acc,value -> acc+ value}
    }
    return total
}
@OptIn(ExperimentalTime::class)
fun ProdTimer.start(): TimeSource.Monotonic.ValueTimeMark = TimeSource.Monotonic.markNow()

@OptIn(ExperimentalTime::class)
fun ProdTimer.stop(start:TimeSource.Monotonic.ValueTimeMark):Duration{
    val end = TimeSource.Monotonic.markNow()
    return end-start
}