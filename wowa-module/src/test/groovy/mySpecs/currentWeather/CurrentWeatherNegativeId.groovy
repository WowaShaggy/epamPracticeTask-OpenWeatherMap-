package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class CurrentWeatherNegativeId extends ExampleTestCase{
    def "The user should send request with incorrect id of the city"() {
        def id = 0

        when: "I send a request with incorrect id of the city"
        def response = currentWeatherApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(id,"${APPid}").build(),502

                /*REQUEST_PARAMS_STRING : "id={id}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : idValue,
                        ],      400*/
        )

        then: "Error message should appear"
        response
    }
}