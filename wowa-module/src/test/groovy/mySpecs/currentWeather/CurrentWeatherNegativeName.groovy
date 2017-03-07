package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class CurrentWeatherNegativeName extends ExampleTestCase{
    def "The user should send request with incorrect name of the city"() {
        def name = "Штормградtttttttttttttt"

        when: "I send a request with incorrect name of the city"
        def response = currentWeatherApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(name,"${APPid}").build(),502

               /* REQUEST_PARAMS_STRING : "q={location}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                        ],404*/
        )

        then: "Error message should appear"
        response

    }
}