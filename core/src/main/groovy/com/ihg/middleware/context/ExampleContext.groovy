package com.ihg.middleware.context

import com.ihg.middleware.client.HttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.ContextConfiguration
/**
 * Created by Uladzimir_Ramaniuk on 11/5/2014.
 */
@ContextConfiguration
class ExampleContext extends AbstractContext {

    @Bean
    PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        def propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer()
        def resources = [
                new ClassPathResource("conf/environments/qa-devtest/example.properties"),
        ]
        propertyPlaceholderConfigurer.locations = resources
        propertyPlaceholderConfigurer.ignoreResourceNotFound = true
        propertyPlaceholderConfigurer
    }

    @Bean
    @Value('${weather.host.url}')
    HttpClient weatherApiHttpClient(String url) {
        new HttpClient(url)
    }

    @Bean
    @Value('${search.regions.host.url}')
    HttpClient searchRegionsApiHttpClient(String url) {
        new HttpClient(url)
    }

    @Bean
    @Value('${five.day.forecast.host.url}')
    HttpClient fiveDayForecastApiHttpClient(String url) {
        new HttpClient(url)
    }

    @Bean
    @Value('${daily.forecast.host.url}')
    HttpClient dailyForecastApiHttpClient(String url) {
        new HttpClient(url)
    }


    @Bean
    @Value('${rectangle.zone.host.url}')
    HttpClient rectangleZoneApiHttpClient(String url) {
        new HttpClient(url)
    }

    //--------------------/ Тут уже новые пошли

    @Bean
    @Value('${test.url}')
    HttpClient currentWeatherApiHttpClient(String url, String version, String tag) {
        new HttpClient(url, "2.5", "weather")
    }


    @Bean
    @Value('${APPid}')
    String APPid(String Appid) {
        Appid
    }



}

