package com.bit.lake.springtestcontainersexample

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.with
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer

@TestConfiguration(proxyBeanMethods = false)
class TestSpringTestcontainersExampleApplication {

    @Bean
    @ServiceConnection
    fun postgresContainer(): PostgreSQLContainer<*> {
        return PostgreSQLContainer("postgres:latest")
    }

}

fun main(args: Array<String>) {
    fromApplication<SpringTestcontainersExampleApplication>().with(
        TestSpringTestcontainersExampleApplication::class
    ).run(*args)
}
