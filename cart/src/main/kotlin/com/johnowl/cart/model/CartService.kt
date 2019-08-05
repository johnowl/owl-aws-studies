package com.johnowl.cart.model

import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class CartService {

    private val repository = ConcurrentHashMap<String, Cart>()

    fun get(id: String) = repository[id] ?: throw CartNotFoundException()

    fun save(cart: Cart) {
        repository[cart.id] = cart
    }

    fun remove(id: String) {
        repository.remove(id)
    }

    fun addProduct(id: String, product: Product): Cart {
        val cart = get(id)
        cart.add(product)
        save(cart)
        return cart
    }

    fun removeProduct(id: String, productId: Int): Cart {
        val cart = get(id)
        cart.remove(productId)
        save(cart)
        return cart
    }

    fun createCart(): Cart {
        val cart = Cart()
        save(cart)
        return cart
    }

}