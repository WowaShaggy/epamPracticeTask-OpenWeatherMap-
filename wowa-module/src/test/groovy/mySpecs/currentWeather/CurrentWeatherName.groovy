package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class CurrentWeatherName extends ExampleTestCase{
    def "The user should check the data by name of the city"() {

        when: "I send a request with the name of the city"
        def response = weatherApiHttpClient.send(
                REQUEST_PARAMS_STRING : "q={location}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : name,
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "City's name, id, country and coordinates should be the same"
        result.name == name
        result.id == id
        result.sys.country == country
        result.coord.lon == lonValue
        result.coord.lat == latValue

        where:
         name         | id         | country |  lonValue | latValue
        "Eindhoven"   | 2756253    | "NL"    | 5.48      | 51.44
        "Irvine"      | 5359777    | "US"    | -117.82   | 33.67
        "Honolulu"    | 5856195    | "US"    | -157.86   | 21.31
        "Porto"       | 2735943    | "PT"    | -8.61     | 41.15
    }
}