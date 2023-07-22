package com.bit.lake.springtestcontainersexample.customers

import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Int>
