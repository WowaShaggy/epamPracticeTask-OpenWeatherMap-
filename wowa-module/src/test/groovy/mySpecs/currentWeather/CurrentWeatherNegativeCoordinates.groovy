package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class CurrentWeatherNegativeCoordinates extends ExampleTestCase {
    def "The user should  send request with incorrect geographic coordinates of the city"() {
        def lonValue = "Wowa"
        def latValue = "rules"

        when: "I send a request with geographic coordinates of the city"
        def response = weatherApiHttpClient.send(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "Error message should appear"
        result.message.toLowerCase().contains("error")
    }
}
