package com.bit.lake.springtestcontainersexample.customer

import com.bit.lake.springtestcontainersexample.customers.Customer
import com.bit.lake.springtestcontainersexample.customers.CustomerRepository
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerIntegrationTest(
    @LocalServerPort
    private val port: Int,
){
    @Autowired
    private lateinit var customerRepository: CustomerRepository
    companion object {
        private val postgres = PostgreSQLContainer("postgres:15-alpine")

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            postgres.start()
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            postgres.stop()
        }

        @DynamicPropertySource
        @JvmStatic
        internal fun configureProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl);
            registry.add("spring.datasource.username", postgres::getUsername);
            registry.add("spring.datasource.password", postgres::getPassword);
        }
    }

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost:$port"
        customerRepository.deleteAll()
    }

    @Test
    fun `create a customer and check successful creation`() {
        val customer = Customer(0, "test", "test")

        given()
            .contentType(ContentType.JSON)
            .body(customer)
            .`when`()
            .post("/customer")
            .then()
            .statusCode(200)

        Assertions.assertEquals(customer.copy(id = 1), customerRepository.findAll().first())
    }
}
