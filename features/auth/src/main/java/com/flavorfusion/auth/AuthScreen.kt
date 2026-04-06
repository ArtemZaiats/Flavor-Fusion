package com.flavorfusion.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.compose.design_system.button.PrimaryButton
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun AuthScreen(
    onAuthSuccess: () -> Unit
) {
    val viewModel: AuthViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    EffectHandler(viewModel = viewModel) { effect ->
        when (effect) {
            is AuthContract.Effect.NavigateToMain -> onAuthSuccess()
            is AuthContract.Effect.ShowError -> Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
        }
    }

    AuthScreen(state = state, onEvent = viewModel::handleEvent)
}

@Composable
fun AuthScreen(
    state: AuthContract.State,
    onEvent: (AuthContract.Event) -> Unit
) {
    val colors = FlavorFusionTheme.colors
    val typography = FlavorFusionTheme.typography

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colors.backgroundPrimary
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .imePadding()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(48.dp))

                Text(
                    text = "Flavor Fusion",
                    style = typography.headingLMedium,
                    color = colors.colorPrimary
                )

                Text(
                    text = "Discover your perfect cocktail",
                    style = typography.bodyMRegular,
                    color = colors.contentSecondary
                )

                Spacer(modifier = Modifier.height(40.dp))

                TabRow(
                    selectedTabIndex = if (state.isLogin) 0 else 1,
                    containerColor = colors.backgroundSecondary,
                    contentColor = colors.colorPrimary,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[if (state.isLogin) 0 else 1]),
                            color = colors.colorPrimary
                        )
                    }
                ) {
                    Tab(
                        selected = state.isLogin,
                        onClick = { onEvent(AuthContract.Event.OnTabChanged(true)) },
                        text = {
                            Text(
                                text = "Login",
                                color = if (state.isLogin) colors.colorPrimary else colors.contentSecondary,
                                style = typography.bodyMMedium
                            )
                        }
                    )
                    Tab(
                        selected = !state.isLogin,
                        onClick = { onEvent(AuthContract.Event.OnTabChanged(false)) },
                        text = {
                            Text(
                                text = "Sign Up",
                                color = if (!state.isLogin) colors.colorPrimary else colors.contentSecondary,
                                style = typography.bodyMMedium
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = state.email,
                    onValueChange = { onEvent(AuthContract.Event.OnEmailChanged(it)) },
                    label = { Text("Email") },
                    singleLine = true,
                    isError = state.emailError != null,
                    supportingText = state.emailError?.let { { Text(it) } },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    colors = outlinedTextFieldColors(colors),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.password,
                    onValueChange = { onEvent(AuthContract.Event.OnPasswordChanged(it)) },
                    label = { Text("Password") },
                    singleLine = true,
                    isError = state.passwordError != null,
                    supportingText = state.passwordError?.let { { Text(it) } },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = if (state.isLogin) ImeAction.Done else ImeAction.Next
                    ),
                    colors = outlinedTextFieldColors(colors),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)

                )

                if (!state.isLogin) {
                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = state.confirmPassword,
                        onValueChange = { onEvent(AuthContract.Event.OnConfirmPasswordChanged(it)) },
                        label = { Text("Confirm Password") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        colors = outlinedTextFieldColors(colors),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
                PrimaryButton(
                    text = if (state.isLogin) "Login" else "Create Account",
                    onClick = { onEvent(AuthContract.Event.OnSubmitClicked) },
                    enabled = !state.isLoading,
                    loading = state.isLoading
                )
            }
        }
    }
}

@Composable
private fun outlinedTextFieldColors(colors: com.flavorfusion.common_ui.theme.FlavorFusionColors) =
    OutlinedTextFieldDefaults.colors(
        focusedBorderColor = colors.inputFieldBorderActive,
        unfocusedBorderColor = colors.contentSecondary.copy(alpha = 0.4f),
        focusedLabelColor = colors.colorPrimary,
        unfocusedLabelColor = colors.contentSecondary,
        cursorColor = colors.colorPrimary,
        focusedTextColor = colors.contentPrimary,
        unfocusedTextColor = colors.contentPrimary,
        errorBorderColor = colors.error,
        errorLabelColor = colors.error,
        errorSupportingTextColor = colors.error
    )

@PreviewLightDark
@Composable
private fun AuthScreenPreview() {
    FlavorFusionTheme {
        AuthScreen(
            state = AuthContract.State(),
            onEvent = {}
        )
    }
}
