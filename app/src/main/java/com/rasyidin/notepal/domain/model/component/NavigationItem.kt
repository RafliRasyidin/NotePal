package com.rasyidin.notepal.domain.model.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rasyidin.notepal.R
import com.rasyidin.notepal.ui.navigation.Screen

data class NavigationItem(
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int,
    @StringRes val name: Int,
    val screen: Screen,
    var isActive: Boolean = false
)

val navigationItems = listOf(
    NavigationItem(
        icon = R.drawable.ic_home_outlined,
        iconSelected = R.drawable.ic_home_filled,
        name = R.string.home,
        screen = Screen.Home
    ),
    NavigationItem(
        icon = R.drawable.ic_task_check_outlined,
        iconSelected = R.drawable.ic_task_check_filled,
        name = R.string.finished,
        screen = Screen.Finished
    ),
    NavigationItem(
        icon = -1,
        iconSelected = -1,
        name = R.string.search,
        screen = Screen.EmptyMenu
    ),
    NavigationItem(
        icon = R.drawable.ic_search,
        iconSelected = R.drawable.ic_search,
        name = R.string.search,
        screen = Screen.SearchNotes
    ),
    NavigationItem(
        icon = R.drawable.ic_user_outlined,
        iconSelected = R.drawable.ic_user_filled,
        name = R.string.profile,
        screen = Screen.Profile
    ),
)
