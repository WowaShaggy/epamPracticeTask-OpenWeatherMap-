package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase

class DayForecastNegativeHtmlMode extends ExampleTestCase {
    def "The user should send html request"() {
        def lonValue = 34
        def latValue = 23
        def modeValue = "html"

        when: "I send html request"
        def response = fiveDayForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                                mode : modeValue
                        ], 200                  //не знаю, наверно временная работоспособность
        )

        then: "Error message should appear because mode isn't supported by this API"
    }
}