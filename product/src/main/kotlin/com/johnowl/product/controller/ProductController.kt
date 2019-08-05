package com.johnowl.product.controller

import com.johnowl.product.domain.Product
import com.johnowl.product.domain.ProductService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(val service: ProductService) {

    @GetMapping("/")
    fun getAll() = service.getAll()

    @ApiResponse(code = 404, message = "Product not found.")
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int) = service.getById(id)

    @PostMapping( "/")
    fun add(@RequestBody product: Product) = service.add(product)

    @ApiResponse(code = 404, message = "Product not found.")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody product: Product) = service.update(id, product)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int) = service.remove(id)
}