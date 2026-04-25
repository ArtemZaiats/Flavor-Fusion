package com.flavorfusion.common_ui.compose.design_system.text_field

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.theme.FlavorFusionColors
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun CredentialTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    supportingText: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val colors = FlavorFusionTheme.colors

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        isError = isError,
        supportingText = supportingText?.let { { Text(it) } },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        colors = outlinedTextFieldColors(colors),
        modifier = modifier,
        shape = RoundedCornerShape(20.dp)
    )
}

@Composable
private fun outlinedTextFieldColors(colors: FlavorFusionColors) =
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