package com.fahimdev.composeboilerplate.presentation.authentication

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahimdev.composeboilerplate.presentation.authentication.components.EmailField
import com.fahimdev.composeboilerplate.presentation.authentication.components.FacebookSignInButton
import com.fahimdev.composeboilerplate.presentation.authentication.components.GoogleSignInButton
import com.fahimdev.composeboilerplate.presentation.authentication.components.PasswordField
import com.fahimdev.composeboilerplate.presentation.authentication.components.SectionDivider
import com.fahimdev.composeboilerplate.presentation.authentication.components.SignUpSection
import com.fahimdev.composeboilerplate.presentation.authentication.events.AuthenticationEvents
import com.fahimdev.composeboilerplate.presentation.base.BaseScreen
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme

@Composable
fun AuthenticationScreen(viewModel: AuthenticationViewModel = hiltViewModel()) {
    val states = viewModel.states.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(key1 = states.message) {
        states.message?.value?.let {
            it.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
    AuthenticationScreenSkeleton(
        signInWithGoogle = {
            viewModel.onEvent(AuthenticationEvents.SignInWithGoogle)
        }
    )
}

@Composable
fun AuthenticationScreenSkeleton(signInWithGoogle: () -> Unit) {
    BaseScreen(title = "Authentication", showTopBar = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Welcome", style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                "Sign in to your account", style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Spacer(modifier = Modifier.padding(24.dp))
            GoogleSignInButton(onPressed = {
                signInWithGoogle()
            })
            Spacer(modifier = Modifier.padding(8.dp))
            FacebookSignInButton(onPressed = {})
            Spacer(modifier = Modifier.height(24.dp))
            SectionDivider()
            Spacer(modifier = Modifier.height(24.dp))
            EmailField(value = "", onValueChange = {})
            Spacer(modifier = Modifier.height(24.dp))
            PasswordField(value = "", onValueChange = {})
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Forgot password?",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Sign in")
            }
            Spacer(modifier = Modifier.height(16.dp))
            SignUpSection()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthenticationScreenPreview() {
    ComposeBoilerplateTheme {
        AuthenticationScreenSkeleton(signInWithGoogle = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AuthenticationScreenPreviewDark() {
    ComposeBoilerplateTheme {
        AuthenticationScreenSkeleton(signInWithGoogle = {})
    }
}