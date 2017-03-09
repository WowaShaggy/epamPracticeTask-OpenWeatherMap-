package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class DayForecastNegativeName extends ExampleTestCase{
    def "The user should send request with incorrect name of the city"() {
        def name = "Штормградtttttttttttttt"
        def mode = "json"

        when: "I send a request with incorrect name of the city"
        def response = daysForecastApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(name,mode,"${APPid}").build(),502

                /*REQUEST_PARAMS_STRING : "q={location}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                mode : modeValue
                        ],200                   // Опять по непонятной причине выдает верный код, должен быть 400*/
        )

        then: "Error message should appear"
        response

    }
}