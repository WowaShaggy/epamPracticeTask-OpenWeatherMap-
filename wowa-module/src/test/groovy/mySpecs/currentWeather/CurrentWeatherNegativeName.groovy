package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase

class CurrentWeatherNegativeName extends ExampleTestCase{
    def "The user should send request with incorrect name of the city"() {
        def locationValue = "Штормградtttttttttttttt"

        when: "I send a request with incorrect name of the city"
        def response = weatherApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "q={location}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                        ],404
        )

        then: "Error message should appear"
        response

    }
}