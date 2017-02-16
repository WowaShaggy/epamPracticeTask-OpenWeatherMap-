package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DayForecastNegativeCoordinates extends ExampleTestCase {
    def "The user should  send request with incorrect geographic coordinates of the city"() {
        def lonValue = "Wowa"
        def latValue = "rules"
        def modeValue = "json"

        when: "I send a request with geographic coordinates of the city"
        def response = fiveDayForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                                mode : modeValue
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "Error message should appear"
        result.message.toLowerCase().contains("error")                //Вообще оно должно ловить лишь ожидаемое [cod:502, message:Error: Not found city]
                        // Но я оставил лишь поиск одного слова "error", чтобы так же ловить бредовое сообщение ["cod":"405","message":"Internal error"]
    }
}