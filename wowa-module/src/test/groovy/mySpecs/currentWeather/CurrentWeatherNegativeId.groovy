package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase

class CurrentWeatherNegativeId extends ExampleTestCase{
    def "The user should send request with incorrect id of the city"() {
        def idValue = 0

        when: "I send a request with incorrect id of the city"
        def response = weatherApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "id={id}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : idValue,
                        ],      400
        )

        then: "Error message should appear"
        response
    }
}