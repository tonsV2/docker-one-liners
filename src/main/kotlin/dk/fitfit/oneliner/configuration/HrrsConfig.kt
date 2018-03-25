package dk.fitfit.oneliner.configuration

import com.vlkan.hrrs.servlet.HrrsFilter
import com.vlkan.hrrs.servlet.HrrsServlet
import com.vlkan.hrrs.servlet.base64.Base64HrrsFilter
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import java.io.File
import java.io.IOException
/*
@Configuration
open class HrrsConfig {

    @Bean
    @Throws(IOException::class)
    open fun provideHrrsFilter(): HrrsFilter {
        val writerTargetFile = File.createTempFile("hrrs-spring-records-", ".csv")
        return Base64HrrsFilter(writerTargetFile)
    }

    @Bean
    open fun provideHrrsServlet(): ServletRegistrationBean<*> {
        val hrrsServlet = HrrsServlet()
        return ServletRegistrationBean(hrrsServlet, "/hrrs")
    }

}
*/