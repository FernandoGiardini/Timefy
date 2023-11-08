package com.br.giardini.timefy.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import com.br.giardini.timefy.model.ProdTimer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

data class TimerListState(
    val nameValue: MutableState<TextFieldValue>,
    val groupValue: MutableState<TextFieldValue>,
    val timers: MutableState<List<ProdTimer>>
)


fun ProdTimer.TotalTime(): Duration {
    var total=0.seconds
    this.sessionHistory?.let {
        total=it.reduce{acc,value -> acc+ value}
    }
    return total
}


fun playPauseButtonIcon(timer:ProdTimer):ImageVector{
    val playIcon= if (timer.active.value){
         Icons.Filled.Pause
    }else{
         Icons.Rounded.PlayArrow
    }
    return playIcon
}


@OptIn(ExperimentalTime::class)
fun ProdTimer.start(): TimeSource.Monotonic.ValueTimeMark = TimeSource.Monotonic.markNow()

@OptIn(ExperimentalTime::class)
fun ProdTimer.stop(start: TimeSource.Monotonic.ValueTimeMark): Duration {
    val end = TimeSource.Monotonic.markNow()
    return end-start
}

