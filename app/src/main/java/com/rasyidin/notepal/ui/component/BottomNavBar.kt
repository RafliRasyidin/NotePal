package com.rasyidin.notepal.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rasyidin.notepal.R
import com.rasyidin.notepal.domain.model.component.navigationItems
import com.rasyidin.notepal.ui.theme.NotePalTheme

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    showNavBar: Boolean = true,
    onAddClick: () -> Unit,
) {
    if (showNavBar) {
        ConstraintLayout {
            val backgroundColor = MaterialTheme.colorScheme.background
            val primaryColor = MaterialTheme.colorScheme.primary
            val outlineColor = MaterialTheme.colorScheme.outline
            val (bottomNavBar, fab) = createRefs()
            NavigationBar(
                modifier = modifier.constrainAs(bottomNavBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currDestination = navBackStackEntry?.destination
                navigationItems.map { navItem ->
                    val selected =
                        currDestination?.hierarchy?.any { it.route == navItem.screen.route } ?: false
                    NavigationBarItem(
                        selected = selected,
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                LocalAbsoluteTonalElevation.current
                            ),
                            selectedIconColor = primaryColor,
                            unselectedIconColor = outlineColor,
                            selectedTextColor = primaryColor,
                            unselectedTextColor = outlineColor
                        ),
                        onClick = {
                            if (navItem.icon != -1) {
                                navController.navigate(navItem.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                        },
                        icon = {
                            if (navItem.icon != -1) {
                                Icon(
                                    modifier = Modifier.size(32.dp),
                                    painter = painterResource(id = if (selected) navItem.iconSelected else navItem.icon),
                                    contentDescription = null
                                )
                            }
                        },
                        label = {
                            if (navItem.icon != -1) {
                                Text(
                                    modifier = Modifier.padding(top = 16.dp),
                                    text = stringResource(id = navItem.name),
                                    style = MaterialTheme.typography.labelSmall,
                                )
                            }
                        }
                    )
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            color = backgroundColor,
                            radius = 96F
                        )
                    }
                    .constrainAs(fab) {
                        bottom.linkTo(bottomNavBar.top, margin = 64.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(bottomNavBar.bottom)
                    },
                shape = CircleShape,
                onClick = onAddClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBottomNavBar() {
    NotePalTheme {
        BottomNavBar(navController = rememberNavController(), onAddClick = {})
    }
}