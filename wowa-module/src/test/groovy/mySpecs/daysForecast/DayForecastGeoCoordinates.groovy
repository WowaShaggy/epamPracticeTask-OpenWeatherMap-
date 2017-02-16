package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DayForecastGeoCoordinates extends ExampleTestCase {

    Random random = new Random();

    def "The user should get forecast list by geographic coordinates of the city"() {

        def latValue = 51.44                                        //33.67   //21.31   //41.15    //51.44
        def lonValue = 5.48                                         //-117.82 //-157.86 //-8.61    //5.48
        def modeValue = "json"

        when: "I send a request with geographic coordinates of the city"
        def response = fiveDayForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lat : latValue,
                                lon : lonValue,
                                mode : modeValue
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "City's name and other list's data should be correct"
        result.city.name == "Eindhoven"                                 //Irvine  //Honolulu //Porto  //Eindhoven
        result.list.size() == result.cnt
        result.list.dt.get(RandomMethod(result.cnt)) > 0
        result.list.main.temp.get(RandomMethod(result.cnt)) >= 200
        result.list.clouds.all.get(RandomMethod(result.cnt)) >= 0
        result.list.wind.speed.get(RandomMethod(result.cnt)) >= 0
        result.list.dt_txt.get(RandomMethod(result.cnt)).startsWith("2017")                    // test pass only during 2017 :)

    }

    public int RandomMethod(int listsSize){
       return random.nextInt(listsSize)
    }
}
