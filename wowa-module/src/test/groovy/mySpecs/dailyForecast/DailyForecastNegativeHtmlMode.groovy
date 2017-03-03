package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase

class DailyForecastNegativeHtmlMode extends ExampleTestCase {
    Random random = new Random();

    def "The user should send html request"() {
        def locationValue = "Brest"
        def cntValue = 16                      // Количество дней, от 1 до 16
        def modeValue = "html"

        when: "I send html request"
        def response = dailyForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING: "q={location}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES:
                        [
                                location: locationValue,
                                mode    : modeValue,
                                cnt     : cntValue,
                        ],400
        )

        then: "Error message should appear"
        response

    }
}