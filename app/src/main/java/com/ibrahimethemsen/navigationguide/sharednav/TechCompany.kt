package com.ibrahimethemsen.navigationguide.sharednav

import androidx.annotation.DrawableRes
import com.ibrahimethemsen.navigationguide.R

val techCompanyList = listOf(
    TechCompany("Sans Tech", R.drawable.st_logo),
    TechCompany("Demirören Teknoloji", R.drawable.dt)
)

data class TechCompany(
    val name: String,
    @DrawableRes val image: Int
)