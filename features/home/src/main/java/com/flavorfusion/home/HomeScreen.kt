package com.flavorfusion.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flavorfusion.common_ui.model.FunFactUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    HomeScreen(state = state)
}

@Composable
fun HomeScreen(state: HomeContract.State) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(FlavorFusionTheme.colors.backgroundPrimary)
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            HeroSection()
        }

        item {
            FeatureHighlightsSection()
        }

        item {
            Text(
                text = "Did You Know?",
                style = FlavorFusionTheme.typography.headingMMedium,
                color = FlavorFusionTheme.colors.contentPrimary
            )
        }

        items(state.facts) { fact ->
            FunFactCard(fact = fact)
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun HeroSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(FlavorFusionTheme.colors.colorPrimary)
            .padding(24.dp)
    ) {
        Text(
            text = "Flavor Fusion",
            style = FlavorFusionTheme.typography.headingLMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Discover recipes & cocktails\nfrom around the world",
            style = FlavorFusionTheme.typography.bodyLRegular,
            color = Color.White.copy(alpha = 0.85f)
        )
    }
}

@Composable
private fun FeatureHighlightsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "What's Inside",
            style = FlavorFusionTheme.typography.headingMMedium,
            color = FlavorFusionTheme.colors.contentPrimary
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FeatureCard(emoji = "🍽️", title = "Recipes", description = "Step-by-step guides for meals from every cuisine", modifier = Modifier.weight(1f))
            FeatureCard(emoji = "🍹", title = "Drinks", description = "Cocktails & mocktails with full ingredient lists", modifier = Modifier.weight(1f))
        }
        FeatureCard(
            emoji = "❤️",
            title = "Favorites",
            description = "Save your go-to recipes and drinks for quick access",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun FeatureCard(
    emoji: String,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = FlavorFusionTheme.colors.cardBackground
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = emoji, fontSize = 28.sp)
            Text(
                text = title,
                style = FlavorFusionTheme.typography.bodyLMedium,
                color = FlavorFusionTheme.colors.contentPrimary
            )
            Text(
                text = description,
                style = FlavorFusionTheme.typography.bodySRegular,
                color = FlavorFusionTheme.colors.contentSecondary
            )
        }
    }
}

@Composable
private fun FunFactCard(fact: FunFactUi) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = FlavorFusionTheme.colors.cardBackground
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(text = fact.emoji, fontSize = 32.sp)
            Spacer(modifier = Modifier.width(12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = fact.title,
                    style = FlavorFusionTheme.typography.bodyLMedium,
                    color = FlavorFusionTheme.colors.contentPrimary
                )
                Text(
                    text = fact.description,
                    style = FlavorFusionTheme.typography.bodyMRegular,
                    color = FlavorFusionTheme.colors.contentSecondary
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    val facts = listOf(
        FunFactUi("🍕", "Pizza Origin", "Pizza was invented in Naples, Italy, in the 18th century."),
        FunFactUi("🍹", "Cocktail History", "The word 'cocktail' first appeared in print in 1806.")
    )
    FlavorFusionTheme {
        HomeScreen(state = HomeContract.State(facts = facts))
    }
}
