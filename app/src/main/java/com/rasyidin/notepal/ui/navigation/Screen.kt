package com.rasyidin.notepal.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Finished : Screen("finished")
    data object AddNotes : Screen("add_notes")
    data object SearchNotes : Screen("search_notes")
    data object EmptyMenu : Screen("empty_menu")
    data object DetailNote : Screen("detail_note")
    data object Profile : Screen("profile")
}