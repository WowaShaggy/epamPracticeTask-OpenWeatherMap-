package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase

class DayForecastNegativeEmptyRequest extends ExampleTestCase {
    def "The user should send empty request"() {

        when: "I send empty request"
        def response = fiveDayForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING: "appid=${APPid}",
                REQUEST_PARAMS_VARIABLES:
                        [
                        ],400
        )

        then: "Error message should appear"
        response
    }
}