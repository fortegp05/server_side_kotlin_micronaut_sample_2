package sample_mn_app_02

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Test

import org.hamcrest.CoreMatchers.notNullValue

import org.hamcrest.CoreMatchers.`is`

import io.micronaut.test.annotation.Sql;

@MicronautTest
@Sql(scripts = ["classpath:sql/tbl_user_seed.sql"])
class UserControllerTest {
    @Test
    fun testGetUserEndpoint(spec: RequestSpecification) {
        spec
            .`when`()
                .get("/user")
            .then()
                .statusCode(200)
                .body(notNullValue())
    }

    @Test
    fun testPostUserEndpoint(spec: RequestSpecification) {
        spec
            .given()
                .param("id", "1")
                .param("name", "UNIT_TEST")
                .param("age", "100")
            .`when`()
                .post("/user")
            .then()
                .statusCode(200)
                .body(`is`("True"))
    }

    @Test
    fun testPutUserEndpoint(spec: RequestSpecification) {
        spec
            .given()
                .formParam("id", "2")
                .formParam("name", "UNIT_TEST_PUT")
                .formParam("age", "25")
            .`when`()
                .put("/user")
            .then()
                .statusCode(200)
                .body(`is`("Updated!"))
    }

    @Test
    fun testDeleteUserEndpoint(spec: RequestSpecification) {
        spec
            .given()
                .formParam("id", "2")
            .`when`()
                .delete("/user")
            .then()
                .statusCode(200)
                .body(`is`("Deleted!"))
    }
}