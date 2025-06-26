package com.rasel.ecommerce_kotlin.screens.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.rasel.ecommerce_kotlin.model.CategoryModel
import com.rasel.ecommerce_kotlin.model.ItemModel
import com.rasel.ecommerce_kotlin.model.SliderModel

class MainViewModel: ViewModel() {
    private val firebaseDb = FirebaseDatabase.getInstance()
    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _categories = MutableLiveData<List<CategoryModel>>()
    private val _recommend = MutableLiveData<List<ItemModel>>()

    val banner: LiveData<List<SliderModel>> = _banner
    val categories: LiveData<List<CategoryModel>> = _categories
    val recommends: LiveData<List<ItemModel>> = _recommend

    fun loadRecommend(){
        val recommendRef = firebaseDb.getReference("Items")
        val query: Query = recommendRef.orderByChild("showRecommended").equalTo(true)

        query.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemModel>()
                for (dataSnapshot in snapshot.children) {
                    val listItem = dataSnapshot.getValue(ItemModel::class.java)
                    if (listItem != null) {
                        lists.add(listItem)
                    }
                }
                _recommend.value = lists
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        }
    fun loadBanners(){
        val bannerRef = firebaseDb.getReference("Banner")

        bannerRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (dataSnapshot in snapshot.children) {
                    val listItem = dataSnapshot.getValue(SliderModel::class.java)
                    if (listItem != null) {
                        lists.add(listItem)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    fun loadCategories(){
        val categoryRef = firebaseDb.getReference("Category")
        categoryRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                for (dataSnapshot in snapshot.children) {
                    val listItem = dataSnapshot.getValue(CategoryModel::class.java)
                    if (listItem != null) {
                        lists.add(listItem)
                    }
                }
                _categories.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}