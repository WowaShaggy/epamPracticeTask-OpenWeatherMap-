package mySpecs.currentWeather

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class CurrentWeatherGeoCoordinates extends ExampleTestCase {
    def "The user should check the data by geographic coordinates of the city"() {
        def lonValue = 5.48                                         //-117.82 //-157.86 //-8.61    //5.48
        def latValue = 51.44                                        //33.67   //21.31   //41.15    //51.44

        when: "I send a request with by geographic coordinates of the city"
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
        result.name == "Eindhoven"                                       //Irvine  //Honolulu //Porto  //Eindhoven
    }
}
