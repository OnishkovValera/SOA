package org.onishkovvalery.vehicleservice.config

import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.ws.config.annotation.EnableWs
import org.springframework.ws.transport.http.MessageDispatcherServlet
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition
import org.springframework.xml.xsd.SimpleXsdSchema
import org.springframework.xml.xsd.XsdSchema

@EnableWs
@Configuration
class SoapWebServiceConfig {

    @Bean
    fun messageDispatcherServlet(context: ApplicationContext): ServletRegistrationBean<MessageDispatcherServlet> {
        val servlet = MessageDispatcherServlet()
        servlet.setApplicationContext(context)
        servlet.setTransformWsdlLocations(true)
        return ServletRegistrationBean(servlet, "/ws/*")
    }

    @Bean(name = ["vehicles"])
    fun defaultWsdl11Definition(vehicleSchema: XsdSchema): DefaultWsdl11Definition {
        val definition = DefaultWsdl11Definition()
        definition.setPortTypeName("VehiclesPort")
        definition.setLocationUri("/ws")
        definition.setTargetNamespace("http://onishkovvalery.org/vehicleservice/soap")
        definition.setSchema(vehicleSchema)
        return definition
    }

    @Bean
    fun vehicleSchema(): XsdSchema {
        return SimpleXsdSchema(ClassPathResource("xsd/vehicle-service.xsd"))
    }
}
