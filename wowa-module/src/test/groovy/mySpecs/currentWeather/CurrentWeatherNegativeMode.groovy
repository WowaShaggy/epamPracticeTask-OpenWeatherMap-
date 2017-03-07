package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class CurrentWeatherNegativeMode extends ExampleTestCase{
    def "The user should send request with incorrect mode"() {
        def name = "Some City"
        def mode = "oops! I am not correct mode"

        when: "I send a request with incorrect mode"
        def response = currentWeatherApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(name,mode,"${APPid}").build(),400

                /*REQUEST_PARAMS_STRING : "q={location}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                mode : modeValue
                        ],400*/
        )

        then: "Error message should appear"
        response

    }
}