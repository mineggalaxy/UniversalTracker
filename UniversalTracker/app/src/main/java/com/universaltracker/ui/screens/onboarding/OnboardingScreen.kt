package com.universaltracker.ui.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onComplete: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 4 })
    val scope = rememberCoroutineScope()
    
    val pages = listOf(
        OnboardingPage(
            title = "Track anything",
            subtitle = "No rules. No pressure.",
            emoji = "📊"
        ),
        OnboardingPage(
            title = "How It Works",
            subtitle = "Create trackers → Log entries → View charts",
            emoji = "🔄"
        ),
        OnboardingPage(
            title = "Powerful Features",
            subtitle = "Charts, statistics, multi-field tracking, reminders",
            emoji = "⚡"
        ),
        OnboardingPage(
            title = "Create Your First Tracker",
            subtitle = "Choose a template or start from scratch",
            emoji = "✨"
        )
    )
    
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    TextButton(onClick = onComplete) {
                        Text("Skip")
                    }
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(pagerState.pageCount) { index ->
                        Box(
                            modifier = Modifier
                                .size(if (pagerState.currentPage == index) 12.dp else 8.dp)
                                .padding(2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Surface(
                                color = if (pagerState.currentPage == index) 
                                    MaterialTheme.colorScheme.primary 
                                else 
                                    MaterialTheme.colorScheme.outline,
                                shape = MaterialTheme.shapes.small
                            ) {
                                Box(modifier = Modifier.size(if (pagerState.currentPage == index) 12.dp else 8.dp))
                            }
                        }
                    }
                }
                
                Button(
                    onClick = {
                        if (pagerState.currentPage < pagerState.pageCount - 1) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            onComplete()
                        }
                    }
                ) {
                    Text(if (pagerState.currentPage < pagerState.pageCount - 1) "Next" else "Get Started")
                }
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = pages[page].emoji,
                    style = MaterialTheme.typography.displayLarge
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = pages[page].title,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = pages[page].subtitle,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

data class OnboardingPage(
    val title: String,
    val subtitle: String,
    val emoji: String
)
