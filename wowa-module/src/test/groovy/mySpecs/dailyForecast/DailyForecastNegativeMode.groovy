package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DailyForecastNegativeMode extends ExampleTestCase{
    Random random = new Random();
    def "The user should send request with incorrect mode"() {
        def locationValue = "Aiur"
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        def modeValue = "Bazinga!"

        when: "I send a request with incorrect mode"
        def response = dailyForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "q={location}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                mode : modeValue,
                                cnt: cntValue,
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "Error message should appear"
        result.message.toLowerCase().contains("error")

    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}