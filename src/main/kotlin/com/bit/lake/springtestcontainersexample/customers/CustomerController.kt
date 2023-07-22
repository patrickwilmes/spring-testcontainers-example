package com.bit.lake.springtestcontainersexample.customers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
class CustomerController(
    private val customerUseCase: CustomerUseCase,
) {

    @PostMapping
    fun create(@RequestBody customer: Customer) {
        customerUseCase.create(customer)
    }

    @PutMapping
    fun update(@RequestBody customer: Customer) {
        customerUseCase.update(customer)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Int) {
        customerUseCase.delete(id)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<Customer>> {
        return ResponseEntity.ok(customerUseCase.getAll())
    }

}
