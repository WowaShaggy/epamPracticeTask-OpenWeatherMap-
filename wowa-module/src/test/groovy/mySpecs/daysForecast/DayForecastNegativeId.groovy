package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase

class DayForecastNegativeId extends ExampleTestCase{
    def "The user should send request with incorrect id of the city"() {
        def idValue = 0
        def modeValue = "json"

        when: "I send a request with incorrect id of the city"
        def response = fiveDayForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "id={id}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : idValue,
                                mode : modeValue
                        ],400
        )

        then: "Error message should appear"
        response
    }
}