package com.rasel.ecommerce_kotlin.screens.productdetails

import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.project1762.Helper.ManagementCart
import com.rasel.ecommerce_kotlin.R
import com.rasel.ecommerce_kotlin.model.ItemModel

@Composable
fun ProductDetailsScreen(
    item: ItemModel,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    onCartClick: () -> Unit,
) {
    var selectedImageUrl by remember { mutableStateOf(item.picUrl.firstOrNull()) }
    var selectedModelIndex by remember { mutableStateOf(-1) }
    val context = LocalContext.current

    //Get parcelableextra
    //item = intent.getParcelableExtra<ItemModel>("item")
    val managementCart = ManagementCart(context)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(top = 24.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            val (back, fav) = createRefs()
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onBackClick }
                    .constrainAs(back) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Image(
                painter = painterResource(R.drawable.fav_icon),
                contentDescription = "",
                modifier = Modifier
                    .clickable { }
                    .constrainAs(fav) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
            Image(
                painter = rememberAsyncImagePainter(model = selectedImageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.lightGrey),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(top = 16.dp)
            )
            LazyRow(
                modifier = Modifier
                    .padding(vertical = 16.dp)
            ) {
                items(item.picUrl){itemUrl: String ->
                    ImageThumbnail(
                        itemUrl = itemUrl,
                        isSelected = itemUrl == selectedImageUrl,
                        onClick = { selectedImageUrl = itemUrl }
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = item.title,
                    fontSize = 23.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 16.dp)
                )
                Text(
                    text = "${item.price} Tk",
                    fontSize = 22.sp
                )
            }
            CustomRatingBar(
                rating = item.rating,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

            ModelSelector(
                models = item.model,
                selectedModelIndex = selectedModelIndex,
                onModelSelected = { index -> selectedModelIndex = index }
            )
            Text(
                text = "Description",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onAddToCartClick()
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(R.color.purple)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Buy Now",
                        fontSize = 18.sp
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.background(
                        color = colorResource(R.color.lightGrey),
                        shape = RoundedCornerShape(8.dp)
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.btn_2),
                        contentDescription = "Add to Cart",
                        tint = Color.Black
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {  }
        }
    }
}

@Composable
fun ModelSelector(
    models: List<String>,
    selectedModelIndex: Int,
    onModelSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .padding(vertical = 16.dp)
    ) {
        itemsIndexed(models) { index, model ->
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .height(48.dp)
                    .then(
                        if (index == selectedModelIndex) {
                            Modifier.border(
                                width = 1.dp,
                                color = colorResource(R.color.lightPurple),
                                shape = RoundedCornerShape(8.dp)
                            )
                        } else {
                            Modifier
                        }
                    )
                    .background(
                        color = if (index == selectedModelIndex) colorResource(R.color.lightPurple) else colorResource(
                            R.color.lightGrey
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { onModelSelected(index) }
                    .padding(horizontal = 16.dp)
            ){
                Text(
                    text = model,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = if (index == selectedModelIndex)
                                    colorResource(R.color.purple)
                            else
                                    colorResource(R.color.black),
                    modifier = Modifier.align(Alignment.Center)

                )
            }
        }
    }
}
    @Composable
    fun CustomRatingBar(rating: Double, modifier: Modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Select Model",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(R.drawable.star),
                contentDescription = "Rating",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Rating $rating: ",
                style = MaterialTheme.typography.body2
            )
        }
    }

    @Composable
    fun ImageThumbnail(
        itemUrl: String,
        isSelected: Boolean,
        onClick: () -> Unit
    ) {
        val bgColor =
            if (isSelected) colorResource(R.color.lightPurple) else colorResource(R.color.lightGrey)
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(55.dp)
                .then(
                    if (isSelected) Modifier.border(
                        1.dp,
                        colorResource(R.color.purple),
                        shape = RoundedCornerShape(8.dp)
                    )
                    else Modifier
                )
                .background(color = bgColor, shape = RoundedCornerShape(8.dp))
                .clickable { onClick }
                .padding(4.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = itemUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            )
        }
    }
