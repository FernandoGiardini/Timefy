package com.br.giardini.timefy.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.time.Duration
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

