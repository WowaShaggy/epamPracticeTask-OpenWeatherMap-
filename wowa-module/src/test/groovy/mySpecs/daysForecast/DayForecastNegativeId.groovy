package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class DayForecastNegativeId extends ExampleTestCase{
    def "The user should send request with incorrect id of the city"() {
        def id = 0
        def mode = "json"

        when: "I send a request with incorrect id of the city"
        def response = daysForecastApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(id,mode,"${APPid}").build(),502

                /*REQUEST_PARAMS_STRING : "id={id}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : idValue,
                                mode : modeValue
                        ],400*/
        )

        then: "Error message should appear"
        response
    }
}