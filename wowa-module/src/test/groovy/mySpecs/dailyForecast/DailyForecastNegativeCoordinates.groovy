package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase

class DailyForecastNegativeCoordinates extends ExampleTestCase {
    Random random = new Random();
    def "The user should  send request with incorrect geographic coordinates of the city"() {
        def lonValue = "Wowa"
        def latValue = "rules"
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        def modeValue = "json"

        when: "I send a request with geographic coordinates of the city"
        def response = dailyForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "lat={lat}&cnt={cnt}&lon={lon}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                                mode : modeValue,
                                cnt: cntValue
                        ],400
        )

        then: "Error message should appear"
        response
    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}