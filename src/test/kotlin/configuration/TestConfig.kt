package configuration

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.example.controllers.BotController
import org.example.servicies.ConfirmationService
import org.example.servicies.EchoService
import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class TestConfig {

    @Bean
    @Primary
    fun echoService(): EchoService = Mockito.mock(EchoService::class.java)

    @Bean
    @Primary
    fun confirmationService(): ConfirmationService = Mockito.mock(ConfirmationService::class.java)

    @Bean
    @Primary
    fun botController(echoService: EchoService, confirmationService: ConfirmationService): BotController =
        BotController(
            echoService = echoService,
            objectMapper = jacksonObjectMapper(),
            confirmationService = confirmationService
        )
}