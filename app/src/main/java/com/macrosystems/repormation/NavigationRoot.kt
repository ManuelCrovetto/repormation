package com.macrosystems.repormation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.gson.Gson
import com.macrosystems.core.domain.RepoData
import com.macrosystems.list.presentation.ListScreenRoot
import com.macrosystems.presentation.DetailScreenRoot

@Composable
fun NavigationRoot(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "discover", modifier = Modifier.systemBarsPadding().padding(8.dp)) {
        repormationSimpleGraph(navHostController)
    }
}

private fun NavGraphBuilder.repormationSimpleGraph(navHostController: NavHostController) {
    navigation(
        startDestination = "list",
        route = "discover"
    ) {
        composable(route = "list") {
            ListScreenRoot(
                onItemClicked = { repo ->
                    val repoJson = Gson().toJson(repo)
                    navHostController.navigate("detail?repoDetails=$repoJson") {
                        popUpTo("list") {
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
        composable(route = "detail?repoDetails={repoDetails}") { backStackEntry ->
            val repoJson = backStackEntry.arguments?.getString("repoDetails")
            val repo = repoJson?.let { Gson().fromJson(it, RepoData::class.java) } // Convert JSON to object
            DetailScreenRoot(repoData = repo)
        }
    }
}