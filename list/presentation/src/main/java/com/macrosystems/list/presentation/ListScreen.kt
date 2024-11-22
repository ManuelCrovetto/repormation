package com.macrosystems.list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.macrosystems.core.domain.RepoData
import com.macrosystems.core.presentation.designsystem.components.RepoCard
import com.macrosystems.core.presentation.designsystem.components.Story
import com.macrosystems.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel


@Composable
fun ListScreenRoot(
    onItemClicked: (RepoData) -> Unit,
    viewModel: ListViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.onAction(ListAction.LoadRepo)
    }
    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            is ListEvent.NavigateToDetails -> onItemClicked(event.repoData)
        }
    }
    ListScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ListScreen(
    state: ListState,
    onAction: (ListAction) -> Unit
) {
    Column {
        LazyRow {
            items(state.users) { user ->
                Story(
                    imageUrl = user.avatar,
                    text = user.name,
                    isSelected = state.selectedUserId == user.id,
                    onClick = { onAction(ListAction.UserSelected(user.id)) }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (state.isLoading && state.error == null) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }

        }
        if (state.error != null) {
            Text(
                state.error.asString(),
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }
        if (!state.isLoading && state.error == null) {
            LazyColumn {
                items(state.repoList) { repo ->
                    RepoCard(
                        name = repo.name,
                        description = repo.repoDescription,
                        stars = repo.stars,
                        language = repo.language
                    ) {
                        onAction(ListAction.NavigateToDetails(repo))
                    }
                }
            }
        }
    }
}