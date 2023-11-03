package com.br.giardini.timefy

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.giardini.timefy.model.ProdTimer
import com.br.giardini.timefy.model.TotalTime
import com.br.giardini.timefy.ui.theme.Pakistan

data class TimerListState(
    val nameValue: MutableState<TextFieldValue>,
    val groupValue: MutableState<TextFieldValue>,
    val activeValue: MutableState<Boolean>,
    val timers: MutableState<List<ProdTimer>>
)
@Composable
fun TimerList(){
    
    val state = remember {
        TimerListState(
            mutableStateOf(TextFieldValue()),
            mutableStateOf(TextFieldValue()),
            mutableStateOf(false),//parei aqui
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
        TextField(value = state.nameValue.value, label = {Text("Nome")},onValueChange = { state.nameValue.value = it }, modifier = Modifier
            .fillMaxWidth(0.5f)
        )
        TextField(value = state.groupValue.value, label = {Text("Categoria")},onValueChange = { state.groupValue.value = it },modifier = Modifier
            .fillMaxWidth(0.7f)
        )

        Button(enabled = state.nameValue.value.text.isNotEmpty(),
            onClick = {
            state.timers.value += ProdTimer(state.nameValue.value.text,state.groupValue.value.text)
        }) {
            Icon(imageVector= Icons.Rounded.AddCircle , contentDescription = "Add Timer", tint = Pakistan)
        }
    }
}

@Composable
fun IndividualTimerComponent(timer:ProdTimer){
    Row {
        Box(modifier = Modifier.fillMaxWidth(0.85f)){
            Column {
                Row(Modifier.padding(8.dp)) {
                    Text(text = timer.nome, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                    Spacer(modifier = Modifier.padding(16.dp,0.dp))
                    Text(text = timer.lastSession.toString(), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Row(Modifier.padding(8.dp)) {

                    Text(text = timer.grupo.toString(), fontWeight = FontWeight.Light, fontSize = 18.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.padding(16.dp,0.dp))
                    Text(text = "Tempo total: ${timer.TotalTime().toString()}", color = Color.Gray)
                }
            }
        }
        Box(modifier = Modifier.fillMaxWidth(1f)){
            IconToggleButton(checked = , onCheckedChange = ){
                Icon(imageVector = Icons.Rounded.PlayArrow, contentDescription ="Play/Pause button",modifier = Modifier.size(30.dp))
            }
        }
    }
}