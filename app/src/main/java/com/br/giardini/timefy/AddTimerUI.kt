package com.br.giardini.timefy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.br.giardini.timefy.model.ProdTimer

data class TextFildState(
    val textFieldValue: MutableState<TextFieldValue>,
    val timers: MutableState<List<ProdTimer>>
)

@Composable
fun AddTimer(){
    Column {
        Row {
            TextField(value = TextFieldValue(), onValueChange = {  })
        }
    }

}