package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper


class DayForecastNegativeEmptyRequest extends ExampleTestCase {
    def "The user should send empty request"() {

        when: "I send empty request"
        def response = fiveDayForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING: "appid=${APPid}",
                REQUEST_PARAMS_VARIABLES:
                        [
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "Error message should appear"
        result.message.toLowerCase().contains("error")

    }
}