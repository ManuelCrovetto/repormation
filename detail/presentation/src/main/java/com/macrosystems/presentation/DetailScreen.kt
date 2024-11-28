package com.macrosystems.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macrosystems.core.domain.Owner
import com.macrosystems.core.domain.RepoData
import com.macrosystems.core.domain.RepoEvent
import com.macrosystems.core.presentation.designsystem.RepormationTheme
import com.macrosystems.core.presentation.designsystem.components.Badge
import com.macrosystems.core.presentation.designsystem.components.RoundedImage
import com.macrosystems.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreenRoot(
    repoData: RepoData?,
    viewModel: DetailViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.onAction(DetailActions.FetchLatestEvent(
            user = repoData?.owner?.login,
            repoName = repoData?.name
        ))
    }
    ObserveAsEvents(viewModel.events) { event ->

    }

    DetailScreen(
        repoData = repoData,
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
fun DetailScreen(
    state: DetailState,
    onAction: (DetailActions) -> Unit,
    repoData: RepoData? = null
) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RoundedImage(isSelected = true, url = repoData?.owner?.profileImageUrl.orEmpty())
            Column {
                Text("${repoData?.owner?.login}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(
                    "${repoData?.owner?.url}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (state.error != null) {
                    Text(
                        state.error.asString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Repo information", fontWeight = FontWeight.Medium, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(repoData?.name.orEmpty(), color = Color.Blue, fontSize = 18.sp)
        Text(
            repoData?.repoUrl.orEmpty(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(24.dp))
        } else {
            if (state.repoEvent != null) {
                Text("Latest events", fontWeight = FontWeight.Medium, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Badge(
                    modifier = Modifier.background(
                        color = Color.Gray.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(4.dp)
                    ),
                    text = state.repoEvent.type,
                    textColor = Color.White
                )
                Text("By: ${state.repoEvent.actorDisplayLogin}")
                Text(state.repoEvent.actorUrl)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    RepormationTheme {
        DetailScreen(
            repoData = RepoData(
                id = 23444,
                name = "X",
                repoUrl = "https://github.com/manuelcrovetto/x",
                repoDescription = "X Clone!",
                owner = Owner(
                    id = 2384,
                    login = "manuelcrovetto",
                    profileImageUrl = "",
                    url = "https://github.com/manuelcrovetto"
                ),
                stars = 4555,
                language = "Kotlin"
            ),
            state = DetailState(
                repoEvent = RepoEvent(
                    type = "Watching",
                    actorDisplayLogin = "ManuelCrovetto",
                    actorUrl = "https://github.com/manuelcrovetto"
                )
            ),
            onAction = {}
        )
    }
}