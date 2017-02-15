package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class CurrentWeatherNegativeName extends ExampleTestCase{
    def "The user should send request with incorrect name of the city"() {
        def locationValue = "Штормград"

        when: "I send a request with incorrect name of the city"
        def response = weatherApiHttpClient.send(
                REQUEST_PARAMS_STRING : "q={location}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "Error message should appear"
        result.message.contains("Error")
        result.cod == "502"

    }
}