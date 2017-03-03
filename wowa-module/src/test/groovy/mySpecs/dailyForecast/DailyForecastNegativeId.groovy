package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase

class DailyForecastNegativeId extends ExampleTestCase{
    Random random = new Random();
    def "The user should send request with incorrect id of the city"() {
        def idValue = 0
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        def modeValue = "json"

        when: "I send a request with incorrect id of the city"
        def response = dailyForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "id={id}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : idValue,
                                mode : modeValue,
                                cnt: cntValue,
                        ],400
        )

        then: "Error message should appear"
        response
    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}