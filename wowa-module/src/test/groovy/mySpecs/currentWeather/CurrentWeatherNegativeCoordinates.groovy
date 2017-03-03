package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase

class CurrentWeatherNegativeCoordinates extends ExampleTestCase {
    def "The user should  send request with incorrect geographic coordinates of the city"() {
        def lonValue = "Wowa"
        def latValue = "rules"

        when: "I send a request with geographic coordinates of the city"
        def response = weatherApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                        ],400
        )

        then: "Error message should appear"
        response
    }
}
