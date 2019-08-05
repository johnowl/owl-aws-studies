package com.johnowl.cart.controller

import com.johnowl.cart.model.CartService
import com.johnowl.cart.model.Product
import io.swagger.annotations.ApiResponse
import org.springframework.web.bind.annotation.*

@RestController
class CartController(private val service: CartService) {

    @ApiResponse(code = 404, message = "Cart not found.")
    @GetMapping("/{id}")
    fun getCartById(@PathVariable("id") id: String) = service.get(id)

    @PostMapping("/")
    fun createCart() = service.createCart()

    @ApiResponse(code = 404, message = "Cart not found.")
    @DeleteMapping("/{id}")
    fun removeCartById(@PathVariable("id") id: String) = service.remove(id)

    @ApiResponse(code = 404, message = "Cart not found.")
    @PostMapping("/{id}/products/")
    fun addProductToCart(@PathVariable("id")id: String, @RequestBody product: Product) = service.addProduct(id, product)

    @ApiResponse(code = 404, message = "Cart not found.")
    @DeleteMapping("/{id}/products/{productId}")
    fun removeProductFromCart(@PathVariable("id")id: String, @PathVariable("productId") productId: Int)
            = service.removeProduct(id, productId)
}