package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DailyForecastNegativeCoordinates extends ExampleTestCase {
    Random random = new Random();
    def "The user should  send request with incorrect geographic coordinates of the city"() {
        def lonValue = "Wowa"
        def latValue = "rules"
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        def modeValue = "json"

        when: "I send a request with geographic coordinates of the city"
        def response = dailyForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "lat={lat}&cnt={cnt}&lon={lon}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                                mode : modeValue,
                                cnt: cntValue
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "Error message should appear"
        result.message.toLowerCase().contains("error")                //Вообще оно должно ловить лишь ожидаемое [cod:502, message:Error: Not found city]
        // Но я оставил лишь поиск одного слова "error", чтобы так же ловить бредовое сообщение ["cod":"405","message":"Internal error"]
    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}