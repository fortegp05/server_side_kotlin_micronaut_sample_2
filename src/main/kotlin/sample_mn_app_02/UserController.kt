package sample_mn_app_02

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.Delete

import io.micronaut.http.annotation.Get

import jakarta.inject.Inject

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.MediaType
import io.micronaut.serde.annotation.Serdeable

@Controller("/user")
class UserController(private val userRepository: UserRepository) {

    @Serdeable
    data class RequestBody(
        val id: Long,
        val name: String,
        val age: Int
    )

    @Serdeable
    data class DeleteRequestBody(
        val id: Int
    )

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post
    fun save(
        @Body body: RequestBody
    ): String {
        userRepository.save(User(body.id, body.name, body.age))
        return "True"
    }

    @Get
    fun fetchAll() = userRepository.findAll().toString()

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Put
    fun update(
        @Body body: RequestBody
    ): String {
        userRepository.update(User(body.id, body.name, body.age))
        return "Updated!"
    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Delete
    fun delete(
        @Body body: DeleteRequestBody
    ): String {
        userRepository.deleteById(body.id)
        return "Deleted!"
    }
}