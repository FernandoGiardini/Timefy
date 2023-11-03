package com.br.giardini.timefy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.br.giardini.timefy.model.ProdTimer
import com.br.giardini.timefy.model.TotalTime
import com.br.giardini.timefy.ui.theme.Pakistan

data class TimerListState(
    val nameValue: MutableState<TextFieldValue>,
    val groupValue: MutableState<TextFieldValue>,
    val timers: MutableState<List<ProdTimer>>
)
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
        state.timers.value.forEach{
            IndividualTimerComponent(timer = it)
        }
    }
}
@Composable
fun AddTimerComponent(state: TimerListState){

    Row {
        TextField(value = TextFieldValue(), label = {Text("Nome")},onValueChange = { state.nameValue.value = it }, modifier = Modifier
            .fillMaxWidth(0.5f)
        )
        TextField(value = TextFieldValue(), label = {Text("Classe")},onValueChange = { state.groupValue.value = it },modifier = Modifier
            .fillMaxWidth(0.7f)
            .background(color = Color.Transparent)
        )

        Button(onClick = {
            state.timers.value += ProdTimer(state.nameValue.value.text,state.groupValue.value.text)
        }) {
            Text(text = "+")
        }
    }
}

@Composable
fun IndividualTimerComponent(timer:ProdTimer){
    Column {
        Box(modifier = Modifier ){
            Row {
                Text(text = timer.nome)
                Text(text = timer.grupo)
            }
        }
        Box(modifier = Modifier){
            Row {
                Text(text = timer.lastSession.toString())
                Text(text = timer.TotalTime().toString())
            }
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Play/pause")
        }
    }
}