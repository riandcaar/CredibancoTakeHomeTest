package com.credibanco.transacciones.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.credibanco.transacciones.ui.screens.auth.TransactionAuthScreen
import com.credibanco.transacciones.ui.screens.auth.TransactionAuthViewModel
import com.credibanco.transacciones.ui.screens.deletion.TransactionDeletion
import com.credibanco.transacciones.ui.screens.deletion.TransactionDeletionViewModel
import com.credibanco.transacciones.ui.screens.list.TransactionListScreen
import com.credibanco.transacciones.ui.screens.list.TransactionListViewModel
import com.credibanco.transacciones.ui.screens.search.TransactionSearchScreen
import com.credibanco.transacciones.ui.screens.search.TransactionSearchViewModel
import com.credibanco.transacciones.ui.screens.transactionInfo.TransactionInfoScreen

@Composable
fun AppNavGraph(navController: NavHostController) {


    val authViewModel: TransactionAuthViewModel = hiltViewModel()
    val deletionViewModel: TransactionDeletionViewModel = hiltViewModel()
    val listViewModel: TransactionListViewModel = hiltViewModel()
    val searchViewModel: TransactionSearchViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Routes.TransactionAuth) {

        composable(Routes.TransactionAuth) {
            TransactionAuthScreen(
                transactionAuthViewModel = authViewModel,
                navController = navController
            )
        }

        composable(Routes.TransactionDeletion) {
            TransactionDeletion(transactionDeletionViewModel = deletionViewModel, navController = navController)
        }
        composable(Routes.TransactionList) {
            TransactionListScreen(transactionListViewModel = listViewModel,navController = navController)
        }

        composable(Routes.SearchTransaction) {
            TransactionSearchScreen(transactionSearchViewModel = searchViewModel,navController = navController)
        }
        composable("${Routes.TransactionInfo}/{receipt}") { backStackEntry ->
            val receipt = backStackEntry.arguments?.getString("receipt") ?: return@composable
            TransactionInfoScreen(
                transactionInfoViewModel = hiltViewModel(),
                navController = navController,
                receipt = receipt
            )
        }
    }
}