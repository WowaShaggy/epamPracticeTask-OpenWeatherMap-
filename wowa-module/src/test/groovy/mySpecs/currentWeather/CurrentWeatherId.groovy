package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class CurrentWeatherId  extends ExampleTestCase{
    def "The user should check the data by id of the city"() {
        def idValue = 5359777                                   //5359777   //5856195  //2735943 //2756253

        when: "I send a request with id of the city"
        def response = weatherApiHttpClient.send(
                REQUEST_PARAMS_STRING : "id={id}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : idValue,
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "City's name should be correct and wind's speed should be positive"
        result.name == "Irvine"                                       //Irvine  //Honolulu //Porto  //Eindhoven
        result.wind.speed >= 0
        }
}
