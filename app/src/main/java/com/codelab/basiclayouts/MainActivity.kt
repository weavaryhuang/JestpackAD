package com.codelab.basiclayouts

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.NonNull
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.codelab.basiclayouts.models.MyData
import com.codelab.basiclayouts.ui.theme.MySootheTheme

class MainActivity : ComponentActivity() {
//    private lateinit var myData: MyData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        myData = ViewModelProvider.AndroidViewModelFactory(this.application).create(MyData::class.java)
//        myData.context = this.applicationContext

        setContent { LoginApp() }
    }
}

// Step: Search bar - Modifiers
@Composable
private fun TitleBar(
    modifier: Modifier = Modifier
){
    Text(
        text = "Dark form dawn",
        style = MaterialTheme.typography.h1,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun LoginPage(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        val myd = context.getSharedPreferences("DataBase", Context.MODE_PRIVATE)
        val editor = myd.edit()
        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val ttt = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Login", style = TextStyle(fontSize = 40.sp))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            placeholder = { Text(text = "Enter User name")},
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = { Text(text = "Enter Passwords")},
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(35.dp))

        TextField(
            readOnly = true,
            value = ttt.value,
//            visualTransformation = PasswordVisualTransformation(),
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            placeholder = { Text(text = "Enter Passwords")},
            onValueChange = { })

        Spacer(modifier = Modifier.height(35.dp))
//        myData = ViewModelProvider.AndroidViewModelFactory().create(MyData::class.java)

        Box(modifier = modifier) {
            Button(
                onClick = {

                    editor.putString("username", username.value.text)
                    editor.putString("passwords", password.value.text)
                    editor.apply()
//                    editor.remove("").commit()

                    ttt.value = username.value },
//                    context.startActivity(Intent(context, Page1::class.java)) },

                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
            ) { Text(text = "Login")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
                //fontFamily = FontFamily.Default
            )
        )
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    // Implement composable here
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(Modifier.height(50.dp))
        TitleBar(Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(35.dp))
        LoginPage(Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(16.dp))
    }
}

// Step: MySoothe App - Scaffold
@Composable
fun LoginApp(){
    MySootheTheme {
        Scaffold(
        ) { padding ->
            LoginScreen(Modifier.padding(padding))
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun LoginAppPreview() {
        LoginApp()
}