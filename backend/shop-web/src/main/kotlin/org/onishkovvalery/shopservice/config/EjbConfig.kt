package org.onishkovvalery.shopservice.config

import org.onishkovvalery.shopservice.ejb.ShopRemote
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jndi.JndiObjectFactoryBean

@Configuration
class EjbConfig {

    @Bean
    fun shopRemote(): ShopRemote {
        val factoryBean = JndiObjectFactoryBean()
        // Use java:app prefix which is more portable for sub-deployments in the same EAR
        factoryBean.setJndiName("java:app/shop-ejb/ShopEJB!org.onishkovvalery.shopservice.ejb.ShopRemote")
        factoryBean.setProxyInterface(ShopRemote::class.java)
        factoryBean.setLookupOnStartup(true)
        factoryBean.afterPropertiesSet()
        return factoryBean.`object` as ShopRemote
    }
}
