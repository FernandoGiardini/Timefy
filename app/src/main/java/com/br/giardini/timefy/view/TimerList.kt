package com.br.giardini.timefy.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.giardini.timefy.model.ProdTimer
import com.br.giardini.timefy.viewmodel.TimerListState
import com.br.giardini.timefy.viewmodel.TotalTime
import com.br.giardini.timefy.viewmodel.playPauseButtonIcon
import com.br.giardini.timefy.viewmodel.stop
import kotlin.time.ExperimentalTime

@Composable
fun TimerList(){

    val state = remember {
        TimerListState(
            mutableStateOf(TextFieldValue()),
            mutableStateOf(TextFieldValue()),
            mutableStateOf(emptyList())
        )
    }

    Column {
        AddTimerComponent(state)
        IndividualTimerComponent(state)

    }
}
@Composable
fun AddTimerComponent(state: TimerListState){

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
    ){
        Column(modifier = Modifier.fillMaxWidth()){
            Text(text = "Inicie uma nova jornada!",fontSize = 22.sp, textAlign = TextAlign.Justify, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            TextField(value = state.nameValue.value, label = {Text("Nome")},onValueChange = { state.nameValue.value = it }, modifier = Modifier
                .fillMaxWidth()
            )
            TextField(value = state.groupValue.value, label = {Text("Categoria")},onValueChange = { state.groupValue.value = it },modifier = Modifier
                .fillMaxWidth()
            )

            TextButton(modifier = Modifier
                .fillMaxWidth(),
                enabled = state.nameValue.value.text.isNotEmpty(),
                onClick = {
                    state.timers.value += ProdTimer(state.nameValue.value.text,state.groupValue.value.text)
                    state.nameValue.value = TextFieldValue()
                    state.groupValue.value = TextFieldValue()
                }
            ) {
                Text(text = "Adicionar Temporizador", fontSize = 18.sp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Icon(imageVector= Icons.Rounded.AddCircle , contentDescription = "Add Timer")
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun IndividualTimerComponent(state: TimerListState){
    val timer = state.timers.value
    timer.forEach{
        val icon = playPauseButtonIcon(it)
        Row {
            Box(modifier = Modifier.fillMaxWidth(0.85f)){
                Column {
                    Row(Modifier.padding(8.dp)) {
                        Text(text = it.nome, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        Spacer(modifier = Modifier.padding(16.dp,0.dp))
                        Text(text = "${it.lastSession?.inWholeSeconds.toString()} segundos.", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                    Row(Modifier.padding(8.dp)) {

                        Text(text = it.grupo.toString(), fontWeight = FontWeight.Light, fontSize = 18.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.padding(16.dp,0.dp))
                        Text(text = "Tempo total: ${it.TotalTime()}", color = Color.Gray)
                    }
                }
            }
            Box(modifier = Modifier.fillMaxWidth(1f)){
                Column {
                    TextButton(onClick = {
                        it.active.value = !it.active.value
                        it.lastSession = it.stop(it.firstTimeMark)
                    } ){
                        Icon(imageVector = icon, contentDescription ="Play/Pause button",modifier = Modifier.size(
                            ButtonDefaults.IconSize))
                    }

                }

            }
        }
    }
}