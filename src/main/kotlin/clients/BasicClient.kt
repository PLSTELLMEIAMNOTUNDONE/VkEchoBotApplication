package org.example.clients

import org.springframework.http.HttpMethod
import org.springframework.util.MultiValueMap

interface BasicClient {
    fun exchange(
        method: String,
        httpMethod: HttpMethod,
        queryParams: MultiValueMap<String, String>
    ) : String
}