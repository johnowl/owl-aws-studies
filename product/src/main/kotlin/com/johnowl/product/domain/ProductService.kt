package com.johnowl.product.domain

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class ProductService {

    private val db = ConcurrentHashMap<Int, Product>()

    fun add(product: Product): Product {
        val id = nextId()
        val dbProduct = product.copy(id = id)
        db[id] = dbProduct
        return dbProduct
    }

    fun update(id: Int, product: Product): Product {

        if(!exists(id)) throw ProductNotFoundException()

        val dbProduct = product.copy(id = id)
        db[id] = dbProduct
        return dbProduct
    }

    fun remove(id: Int) {
        db.remove(id)
    }

    fun getAll() = db.values.toList()

    fun getById(id: Int) = db[id] ?: throw ProductNotFoundException()

    private fun nextId(): Int {
        val max = db.maxBy { it.value.id } ?: return 1
        return max.value.id + 1
    }

    private fun exists(id: Int) = db[id] != null

}