package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DayForecastName extends ExampleTestCase{
    def "The user should check the data by name of the city"() {
        def locationValue = "Irvine"                                    //Irvine  //Honolulu //potru //Eindhoven
        def modeValue = "json"

        when: "I send a request with the name of the city"
        def response = fiveDayForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "q={location}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                mode : modeValue
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "City's name, id, country and coordinates should be the same, and list's size should be 36"
        result.city.name == "Irvine"                                      //Irvine  //Honolulu //Porto //Eindhoven
        result.city.country == "US"                                         //US      //US       //PT      //NL
        result.city.id == 5359777                                           //5359777  //5856195  //2735943 //2756253
        result.city.coord.lat.toString().startsWith("33.6")                   //33.6   //21.3   //41.1    //51.4
        result.city.coord.lon.toString().startsWith("-117.8")                   //-117.8 //-157.8 //-8.6   //5.4
        result.list.size() == result.cnt
    }
}
