package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class CurrentWeatherGeoCoordinates extends ExampleTestCase {
    def "The user should check the data by geographic coordinates of the city"() {
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

        then: "City's name should be correct"
        result.name == name

        where:
        lonValue | latValue | name
        5.48     | 51.44    |"Eindhoven"
        -117.82  | 33.67    |"Irvine"
        -157.86  | 21.31    |"Honolulu"
        -8.61    | 41.15    |"Porto"


    }
}
