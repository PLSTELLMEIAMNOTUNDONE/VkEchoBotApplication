package controllers

import configuration.TestConfig
import model.MessageForEchoDto
import servicies.EchoService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.io.File
import java.io.FileReader

@WebMvcTest
@ContextConfiguration(classes = [TestConfig::class])
class BotControllerTest(
    @Autowired
    private val mockMcv: MockMvc,
    @Autowired
    private val echoService: EchoService
) {

    private fun getFileContent(filename: String) =
        File(filename).let {
            FileReader(it).readText()
        }

    @Test
    fun shouldInvoke() {
        mockMcv.perform(
            MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getFileContent("src/test/resources/test_cases/bot_controller_test.json"))
        )
        Mockito.verify(echoService, times(1))
            .echo(
                MessageForEchoDto(
                    0L,
                    "test_text"
                )
            )
    }
}