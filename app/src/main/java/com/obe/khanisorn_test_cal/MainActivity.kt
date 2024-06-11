package com.obe.khanisorn_test_cal

import android.graphics.SumPathEffect
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.obe.khanisorn_test_cal.ui.theme.Khanisorn_test_calTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Khanisorn_test_calTheme {
                val calViewModel: CalViewModel by viewModels()
                AppView(calViewModel)
            }
        }
    }
}

@Composable
fun AppView(calViewModel: CalViewModel = viewModel()){
    val number = calViewModel.number

    MyCal(number.value,
        {calViewModel.addDigit(it)},
        {calViewModel.addDot(it)},
        {calViewModel.operate(it)},
        {calViewModel.equal()}
    )
}

@Composable
fun MyCal(value: String, addDigit: (String) -> Unit, addDot: (String) -> Unit, operate:(String) -> Unit,equal: () -> Unit){
    Surface() {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(1.dp, Color.Black))
                    .weight(1f)
                    .background(Color.Gray), verticalAlignment = Alignment.CenterVertically
            ) {
                    Text(
                        text = value,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        textAlign = TextAlign.End,
                        fontSize = 60.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(4f)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .weight(3f)){
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)){
                        MadeButtonDigit(1f,"1"){addDigit(it)}
                        MadeButtonDigit(1f,"2"){addDigit(it)}
                        MadeButtonDigit(1f,"3"){addDigit(it)}
                    }
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)){
                        MadeButtonDigit(1f,"4"){addDigit(it)}
                        MadeButtonDigit(1f,"5"){addDigit(it)}
                        MadeButtonDigit(1f,"6"){addDigit(it)}

                    }
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)){
                        MadeButtonDigit(1f,"7"){addDigit(it)}
                        MadeButtonDigit(1f,"8"){addDigit(it)}
                        MadeButtonDigit(1f,"9"){addDigit(it)}
                    }
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)){
                        MadeButtonDigit(2f,"0") {addDigit(it)}
                        MadeButtonDigit(1f,".") {addDot(it)}
                    }
                }
                Column(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)){
                    MadeButtonOperator(1f, "+"){operate(it)}
                    MadeButtonOperator(1f, "-"){operate(it)}
                    MadeButtonOperator(2f, "="){equal()}
                }
            }
        }
    }
}

@Composable
fun RowScope.MadeButtonDigit(weight: Float, text: String, AddDigit: (String) -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .border(BorderStroke(1.dp, Color.Black))
        .weight(weight)
        .clickable {
            AddDigit(text)
        }
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.BottomEnd),
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ColumnScope.MadeButtonOperator(weight: Float, text: String, AddOpt: (String)-> Unit={}){
    Box(modifier = Modifier
        .fillMaxSize()
        .border(BorderStroke(1.dp, Color.Black))
        .weight(weight)
        .clickable { AddOpt(text) }
    ) {
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.BottomEnd),
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold
        )
    }
}