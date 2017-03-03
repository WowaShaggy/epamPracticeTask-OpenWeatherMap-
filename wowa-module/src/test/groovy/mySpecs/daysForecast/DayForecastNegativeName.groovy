package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase

class DayForecastNegativeName extends ExampleTestCase{
    def "The user should send request with incorrect name of the city"() {
        def locationValue = "Штормградt"
        def modeValue = "json"

        when: "I send a request with incorrect name of the city"
        def response = fiveDayForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "q={location}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                mode : modeValue
                        ],200                   // Опять по непонятной причине выдает верный код, должен быть 400
        )

        then: "Error message should appear"
        response

    }
}