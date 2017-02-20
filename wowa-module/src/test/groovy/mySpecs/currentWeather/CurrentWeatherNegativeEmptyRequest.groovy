package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class CurrentWeatherNegativeEmptyRequest extends ExampleTestCase{
    def "The user should send empty request"() {

        when: "I send empty request "
        def response = weatherApiHttpClient.send(
                REQUEST_PARAMS_STRING : "appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "Error message should appear"
        result.message.toLowerCase().contains("error")

    }
}