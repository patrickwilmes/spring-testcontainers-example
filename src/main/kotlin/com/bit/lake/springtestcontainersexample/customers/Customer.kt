package com.bit.lake.springtestcontainersexample.customers

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customers")
data class Customer(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Int,
    val firstname: String,
    val surname: String,
)
