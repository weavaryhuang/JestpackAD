package com.codelab.basiclayouts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelab.basiclayouts.models.ConnViewModel
import com.codelab.basiclayouts.ui.theme.MySootheTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        val composableScope = rememberCoroutineScope()
        val context = LocalContext.current
        val myd = context.getSharedPreferences("DataBase", Context.MODE_PRIVATE)
        val editor = myd.edit()
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val permissionInfo = remember { mutableStateOf("") }

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

        Text(text = permissionInfo.value, style = TextStyle(fontSize = 20.sp), color = Color.Red)

//        TextField(
//            readOnly = true,
//            value = permissionInfo.value,
////            visualTransformation = PasswordVisualTransformation(),
//            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
////            placeholder = { Text(text = "Enter Passwords")},
//            onValueChange = { })

        Spacer(modifier = Modifier.height(35.dp))
//        myData = ViewModelProvider.AndroidViewModelFactory().create(MyData::class.java)

        Box(modifier = modifier) {
            Button(
                onClick = {

                            editor.putString("username", username.value)
                            editor.putString("passwords", password.value)
                            editor.apply()
        //                    editor.remove("").commit()
                            composableScope.launch {
                                val connViewModel = ConnViewModel("")
                                val result1 = try {
                                    connViewModel.checkid(username.value, password.value)
                                } catch (e: Exception) { "Not working" }
                                Log.d("InClass", result1)
                                if (result1 == "1") {
                                    context.startActivity(Intent(context, Page1::class.java))
                                }else{
                                    permissionInfo.value = "User info wrong"
                                }
                            }
                          },
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