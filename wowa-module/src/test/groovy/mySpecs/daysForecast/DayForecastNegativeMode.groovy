package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DayForecastNegativeMode extends ExampleTestCase{
    def "The user should send request with incorrect mode"() {
        def locationValue = "Motunui"
        def modeValue = "What's beyond that line, will I cross that line?"

        when: "I send a request with incorrect mode"
        def response = fiveDayForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "q={location}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                mode : modeValue
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "Error message should appear"
        result.message.toLowerCase().contains("error")

    }
}