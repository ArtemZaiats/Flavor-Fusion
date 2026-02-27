package com.flavorfusion.settings.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.PersonPlaceholder
import com.flavorfusion.common_ui.model.profile.ProfileUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    profile: ProfileUi
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (profile.profileImage?.isEmpty() == true) {
            Image(
                imageVector = AppIcons.PersonPlaceholder,
                contentDescription = "Profile image",
                modifier = Modifier.size(60.dp),
                colorFilter = ColorFilter.tint(FlavorFusionTheme.colors.contentPrimary)
            )
        } else {
            AsyncImage(
                model = profile.profileImage,
                contentDescription = "Profile image",
                modifier = Modifier.size(60.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = profile.firstName.orEmpty(),
                    fontWeight = FontWeight(500),
                    color = FlavorFusionTheme.colors.contentPrimary
                )
                Text(
                    text = profile.lastName.orEmpty(),
                    fontWeight = FontWeight(500),
                    color = FlavorFusionTheme.colors.contentPrimary
                )
            }
            Text(
                text = profile.email,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight(400),
                color = FlavorFusionTheme.colors.contentSecondary
            )
        }
    }
}

@PreviewLightDark
@Composable
fun ProfileHeaderPreview() {
    FlavorFusionTheme {
        ProfileHeader(
            modifier = Modifier
                .background(color = FlavorFusionTheme.colors.backgroundPrimary)
                .padding(8.dp),
            profile = ProfileUi(
                email = "william.henry.harrison@example-pet-store.com",
                firstName = "John",
                lastName = "Doe"
            )
        )
    }
}

