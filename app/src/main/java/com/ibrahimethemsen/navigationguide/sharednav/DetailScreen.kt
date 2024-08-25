package com.ibrahimethemsen.navigationguide.sharednav

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScreen(
    id: Int,
    techItem: TechCompany,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    popUp : () -> Unit,
    navigateClick: () -> Unit,
) {
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Image(
                modifier = Modifier.Companion
                    .clickable {
                        popUp()
                        navigateClick()
                    }
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "image-$id"),
                        animatedContentScope
                    )
                    .aspectRatio(1f)
                    .fillMaxWidth(),
                painter = painterResource(id = techItem.image),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.Companion
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(
                            key = "text-$id"
                        ),
                        animatedContentScope
                    )
                    .fillMaxWidth(),
                text = techItem.name,
                fontSize = 18.sp
            )
        }
    }
}