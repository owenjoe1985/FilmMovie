package com.example.filmmovie.repository

import com.example.filmmovie.api.ApiServices
import com.example.filmmovie.models.register.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api:ApiServices) {

    suspend fun registerUser(body: BodyRegister) = api.registerUser(body)


}