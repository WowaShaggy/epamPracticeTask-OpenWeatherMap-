package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class CurrentWeatherNegativeCoordinates extends ExampleTestCase {
    def "The user should  send request with incorrect geographic coordinates of the city"() {
        def lonValue = 10000000.0000
        def latValue = 100000.000

        when: "I send a request with geographic coordinates of the city"
        def response = currentWeatherApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(lonValue,latValue,"${APPid}").build(),502

                /*REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                        ],400*/
        )

        then: "Error message should appear"
        response
    }
}
