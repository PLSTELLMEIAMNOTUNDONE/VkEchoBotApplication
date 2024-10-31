package servicies

import clients.VkConfirmation
import org.springframework.stereotype.Service

@Service
class ConfirmationService(
    private val vkConfirmation: VkConfirmation
) {
    fun getConfirmationCode(groupId: Long?): String {
        return groupId?.let {  vkConfirmation.getConfirmationCode(it) }
            ?: throw IllegalArgumentException("group id is null")
    }
}