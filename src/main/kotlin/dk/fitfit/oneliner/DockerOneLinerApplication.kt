package dk.fitfit.oneliner

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.hibernate.search.jpa.Search
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import javax.persistence.EntityManager

@SpringBootApplication
class DockerOneLinerApplication {
    @Bean
    fun mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter {
        val mapper = ObjectMapper()
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        return MappingJackson2HttpMessageConverter(mapper)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(DockerOneLinerApplication::class.java, *args)
}

@Bean
fun search(entityManager: EntityManager) {
    val fullTextEntityManager = Search.getFullTextEntityManager(entityManager)
    fullTextEntityManager.createIndexer().startAndWait()
}
