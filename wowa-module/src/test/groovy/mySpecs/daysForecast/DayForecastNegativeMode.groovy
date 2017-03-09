package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class DayForecastNegativeMode extends ExampleTestCase{
    def "The user should send request with incorrect mode"() {
        def name = "Motunui"
        def mode = "What's beyond that line, will I cross that line?"

        when: "I send a request with incorrect mode"
        def response = daysForecastApiHttpClient.sendAndVerifyResponseStatus(

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