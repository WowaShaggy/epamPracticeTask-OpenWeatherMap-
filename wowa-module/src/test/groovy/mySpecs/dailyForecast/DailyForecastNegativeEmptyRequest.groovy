package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class DailyForecastNegativeEmptyRequest extends ExampleTestCase{
    def "The user should send empty request"() {

        when: "I send empty request"
        def response = dailyForecastApiHttpClientNew.sendAndVerifyResponseStatus(

                new RequestBuilder("${APPid}").build(),502

               /* REQUEST_PARAMS_STRING : "appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                        ],400*/
        )

        then: "Error message should appear"
        response

    }
}