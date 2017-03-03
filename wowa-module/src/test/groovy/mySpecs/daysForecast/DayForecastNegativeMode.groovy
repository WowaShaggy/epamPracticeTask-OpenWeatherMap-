package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase

class DayForecastNegativeMode extends ExampleTestCase{
    def "The user should send request with incorrect mode"() {
        def locationValue = "Motunui"
        def modeValue = "What's beyond that line, will I cross that line?"

        when: "I send a request with incorrect mode"
        def response = fiveDayForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "q={location}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                mode : modeValue
                        ],400
        )

        then: "Error message should appear"
        response

    }
}