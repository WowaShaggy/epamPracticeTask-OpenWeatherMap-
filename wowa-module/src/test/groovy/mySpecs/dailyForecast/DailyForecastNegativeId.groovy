package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DailyForecastNegativeId extends ExampleTestCase{
    Random random = new Random();
    def "The user should send request with incorrect id of the city"() {
        def idValue = 0
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        def modeValue = "json"

        when: "I send a request with incorrect id of the city"
        def response = dailyForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "id={id}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : idValue,
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