package org.example.clients

import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap

@Component
class VkConfirmation(
    private val vkBasicClient: BasicClient

) {
    private val operation = Operation.GET_CONFIRM_CODE

    fun getConfirmationCode(groupId: Long): String {
        val params = LinkedMultiValueMap<String, String>()
        params["group_id"] = groupId.toString()
        return operation.action(
            params,
            vkBasicClient
        )
    }
}