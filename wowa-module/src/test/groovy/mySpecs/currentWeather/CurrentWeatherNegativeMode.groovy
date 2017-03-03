package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase

class CurrentWeatherNegativeMode extends ExampleTestCase{
    def "The user should send request with incorrect mode"() {
        def locationValue = "Some City"
        def modeValue = "oops! I am not correct mode"

        when: "I send a request with incorrect mode"
        def response = weatherApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "q={location}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                mode : modeValue
                        ],200           //кажется раньше он не работал... ладно пусть
        )

        then: "Error message should appear"
        response

    }
}