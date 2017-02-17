package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DayForecastNegativeHtmlMode extends ExampleTestCase {
    def "The user should send html request"() {
        def lonValue = 34
        def latValue = 23
        def modeValue = "html"

        when: "I send html request"
        def response = fiveDayForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                                mode : modeValue
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "Error message should appear because mode isn't supported by this API"
        result.message.toLowerCase().contains("error")
    }
}