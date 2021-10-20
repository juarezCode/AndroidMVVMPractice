package com.juarez.mvvmpractice.data;

import com.juarez.mvvmpractice.api.WebService
import com.juarez.mvvmpractice.models.ServiceResponse
import com.juarez.mvvmpractice.models.User
import kotlinx.coroutines.delay
import java.lang.Exception

class UserRepository {

    suspend fun getUsers(name: String): ServiceResponse<List<User>> {
        var serviceResponse = ServiceResponse<List<User>>(null, null)
        try {
            val response = WebService.service().getUsers()
            if (response.isSuccessful) serviceResponse = ServiceResponse(response.body(), null)
        } catch (e: Exception) {
            serviceResponse = ServiceResponse(null, e.message.toString())
        }
        return serviceResponse
    }

    suspend fun getUser(userId: Int): ServiceResponse<User> {
        delay(1000)
        var serviceResponse = ServiceResponse<User>(null, null)
        try {
            val response = WebService.service().getUser(userId)
            if (response.isSuccessful) {
                serviceResponse = ServiceResponse(response.body(), null)
            }
        } catch (e: Exception) {
            serviceResponse = ServiceResponse(null, e.message.toString())
        }
        return serviceResponse
    }
}
