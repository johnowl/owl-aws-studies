package com.johnowl.cart.model

import java.util.*

class Cart {

    val id = UUID.randomUUID().toString()

    val items = mutableListOf<Product>()

    val total: Double
        get() = items.map { it.amount * it.price }.sum()

    fun add(product: Product) {
        items.add(product)
    }

    fun remove(id: Int) {
        val product = items.find { it.id == id} ?: return
        items.remove(product)
    }
}