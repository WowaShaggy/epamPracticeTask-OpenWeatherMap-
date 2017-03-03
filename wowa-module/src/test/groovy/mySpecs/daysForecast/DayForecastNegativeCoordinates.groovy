package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase

class DayForecastNegativeCoordinates extends ExampleTestCase {
    def "The user should  send request with incorrect geographic coordinates of the city"() {
        def lonValue = "Wowa"
        def latValue = "rules"
        def modeValue = "json"

        when: "I send a request with geographic coordinates of the city"
        def response = fiveDayForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                                mode : modeValue
                        ],400
        )

        then: "Error message should appear"
        response
    }
}