package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class CurrentWeatherNegativeEmptyRequest extends ExampleTestCase{
    def "The user should send empty request"() {

        when: "I send empty request "
        def response = currentWeatherApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder("${APPid}").build(),502

                /*REQUEST_PARAMS_STRING : "appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                        ],400*/
        )
        then: "Error message should appear"
        response

    }
}