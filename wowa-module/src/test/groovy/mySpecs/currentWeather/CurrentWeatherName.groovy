package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class CurrentWeatherName extends ExampleTestCase{
    def "The user should check the data by name of the city"() {
        def locationValue = "Eindhoven"                                    //Irvine  //Honolulu //potru //Eindhoven

        when: "I send a request with the name of the city"
        def response = weatherApiHttpClient.send(
                REQUEST_PARAMS_STRING : "q={location}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "City's name, id, country and coordinates should be the same"
        result.name == "Eindhoven"                                       //Irvine  //Honolulu //Porto   //Eindhoven
        result.id == 2756253                                             //5359777  //5856195  //2735943 //2756253
        result.sys.country == "NL"                                       //US      //US       //PT      //NL
        result.coord.lon == 5.48                                         //-117.82 //-157.86 //-8.61    //5.48
        result.coord.lat == 51.44                                        //33.67   //21.31   //41.15    //51.44

    }
}