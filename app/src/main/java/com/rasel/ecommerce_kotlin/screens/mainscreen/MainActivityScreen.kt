package com.rasel.ecommerce_kotlin.screens.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rasel.ecommerce_kotlin.R
import com.rasel.ecommerce_kotlin.model.CategoryModel
import com.rasel.ecommerce_kotlin.model.ItemModel
import com.rasel.ecommerce_kotlin.model.SliderModel
import com.rasel.ecommerce_kotlin.screens.mainscreen.banner.AutoSlidingCarousel
import com.rasel.ecommerce_kotlin.screens.mainscreen.banner.SectionTitle
import com.rasel.ecommerce_kotlin.screens.mainscreen.category.CategoryList
import com.rasel.ecommerce_kotlin.screens.navigations.BottomNavBar

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview
fun MainActivityScreen() {
    val viewmodel = MainViewModel()
    val banners = remember { mutableStateListOf<SliderModel>() }
    val categories = remember { mutableStateListOf<CategoryModel>() }
    val recommends = remember { mutableStateListOf<ItemModel>() }
    val filteredProduct = remember { mutableStateListOf<ItemModel>() }

    var showBannerLoading by remember { mutableStateOf(true) }
    var showCategoryLoading by remember { mutableStateOf(true) }
    var showRecommendLoading by remember { mutableStateOf(true) }
    var showProductLoading by remember { mutableStateOf(true) }
    var selectedCategoryId by remember { mutableStateOf("") }

    val products by viewmodel.filteredProducts.observeAsState()



    //Load banner
    LaunchedEffect(Unit) {
        viewmodel.loadBanners()
        viewmodel.banner.observeForever {
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }
    LaunchedEffect(Unit) {
        viewmodel.loadCategories()
        viewmodel.categories.observeForever {
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false
        }
    }
    LaunchedEffect(Unit) {
        viewmodel.loadRecommend()
        viewmodel.recommends.observeForever {
            recommends.clear()
            recommends.addAll(it)
            showRecommendLoading = false
        }
    }
    LaunchedEffect(selectedCategoryId){
        viewmodel.loadProductByCategory(selectedCategoryId)
        viewmodel.filteredProducts.observeForever{
            filteredProduct.clear()
            filteredProduct.addAll(it)
            showProductLoading = false
        }
    }

    ConstraintLayout(
        modifier = Modifier.background(Color.White)
    ) {
        val (scrollList, bottomMenu) = createRefs()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(scrollList) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Welcome Back",
                            color = Color.Black
                        )
                        Text(
                            text = "Rasel",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row {
                        Image(
                            painter = painterResource(R.drawable.fav_icon),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(
                            painter = painterResource(R.drawable.search_icon),
                            contentDescription = null
                        )
                    }
                }
            }
            item {
                if (showBannerLoading){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }else{
                    AutoSlidingCarousel(banners = banners)
                }
            }
            item{
                SectionTitle(title = "Categories", actionText = "See All")
            }
            item {
                if (showCategoryLoading){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }else{
                    CategoryList(categories){
                        selectedCategoryId = it.toString()
                    }
                }
            }
            item {
                if (showRecommendLoading){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }else{
                    if (selectedCategoryId.isEmpty()){
                        SectionTitle(title = "Recommendations", actionText = "See All")
                        ListItems(recommends)
                    }
                }
            }
            item {
                if (selectedCategoryId.isNotEmpty()){
                    if (showProductLoading){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }else{
                        SectionTitle(title = "Products", actionText = "See All")
                        ListItems(filteredProduct)
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
        BottomNavBar(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottomMenu) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onItemClick = {}
        )
    }
}