package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class DayForecastNegativeCoordinates extends ExampleTestCase {
    def "The user should  send request with incorrect geographic coordinates of the city"() {
        def lon = 10000000.0000
        def lat = 100000.000
        def mode = "json"

        when: "I send a request with geographic coordinates of the city"
        def response = daysForecastApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(lon,lat,mode,"${APPid}").build(),502

               /* REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                                mode : modeValue
                        ],400*/
        )

        then: "Error message should appear"
        response
    }
}