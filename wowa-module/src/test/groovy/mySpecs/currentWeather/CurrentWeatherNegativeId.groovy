package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class CurrentWeatherNegativeId extends ExampleTestCase{
    def "The user should send request with incorrect id of the city"() {
        def idValue = 0

        when: "I send a request with incorrect id of the city"
        def response = weatherApiHttpClient.send(
                REQUEST_PARAMS_STRING : "id={id}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : idValue,
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "Error message should appear"
        result.message.contains("Error")
        result.cod == "502"
    }
}