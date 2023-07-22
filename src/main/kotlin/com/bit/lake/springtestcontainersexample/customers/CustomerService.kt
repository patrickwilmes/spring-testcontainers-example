package com.bit.lake.springtestcontainersexample.customers

import org.springframework.stereotype.Service

interface CustomerUseCase {
    fun create(customer: Customer)
    fun update(customer: Customer)
    fun delete(id: Int)
    fun getAll(): List<Customer>
}

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
) : CustomerUseCase {

    override fun create(customer: Customer) {
        customerRepository.save(customer)
    }

    override fun update(customer: Customer) {
        customerRepository.save(customer)
    }

    override fun delete(id: Int) {
        customerRepository.deleteById(id)
    }

    override fun getAll(): List<Customer> {
        return customerRepository.findAll()
    }
}
