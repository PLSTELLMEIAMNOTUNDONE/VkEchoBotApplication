package clients

import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class VkBasicClient(
    @Value("\${vk.version}") private val vkVersion: String,
    @Value("\${vk.api.address}") private val baseUrl: String,
    @Value("\${vk.access.token}") private val accessToken: String
) {

    // private val https://<адрес-сервера>/method/<имя-API-метода>?<параметры>;
    fun exchange(
        method: String,
        httpMethod: HttpMethod,
        queryParams: MultiValueMap<String, String>
    ): String {

        queryParams.add("v", vkVersion)
        queryParams.add("access_token", accessToken)
        return runBlocking {
            WebClient.create(baseUrl)
                .method(httpMethod)
                .uri { builder -> builder.path(method).queryParams(queryParams).build() }
                .retrieve()
                .awaitBody<String>()
        }
    }
}