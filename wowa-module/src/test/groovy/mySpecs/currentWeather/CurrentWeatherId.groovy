package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class CurrentWeatherId  extends ExampleTestCase{
    def "The user should check the data by id of the city"() {

        when: "I send a request with id of the city"
        def response = weatherApiHttpClient.send(
                REQUEST_PARAMS_STRING : "id={id}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : id,
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)


        then: "City's name should be correct and wind's speed should be positive"
        result.name == name
        result.wind.speed >= 0

        where:
        id         |  name
        2756253    |  "Eindhoven"
        5359777    |  "Irvine"
        5856195    |  "Honolulu"
        2735943    |  "Porto"

    }
}
