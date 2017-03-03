package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase

class DailyForecastNegativeEmptyRequest extends ExampleTestCase{
    def "The user should send empty request"() {

        when: "I send empty request"
        def response = dailyForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                        ],400
        )

        then: "Error message should appear"
        response

    }
}