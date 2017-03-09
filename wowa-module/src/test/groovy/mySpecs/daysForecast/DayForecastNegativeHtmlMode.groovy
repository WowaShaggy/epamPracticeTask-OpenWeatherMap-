package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class DayForecastNegativeHtmlMode extends ExampleTestCase {
    def "The user should send html request"() {
        def lon = 34
        def lat = 23
        def mode = "html"

        when: "I send html request"
        def response = daysForecastApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(lon,lat,mode,"${APPid}").build(),400     //502

                /*REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                                mode : modeValue
                        ], 200                  //не знаю, наверно временная работоспособность*/
        )

        then: "Error message should appear because mode isn't supported by this API"
    }
}