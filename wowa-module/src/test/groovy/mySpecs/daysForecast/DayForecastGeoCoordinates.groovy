package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DayForecastGeoCoordinates extends ExampleTestCase {

    Random random = new Random();

    def "The user should get forecast list by geographic coordinates of the city"() {
        def modeValue = "json"

        when: "I send a request with geographic coordinates of the city"
        def response = fiveDayForecastApiHttpClient.sendAndVerifyResponseStatus(
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
        result.city.name == name
        result.list.size() == result.cnt
        result.list.dt.get(RandomMethod(result.cnt)) > 0
        result.list.main.temp.get(RandomMethod(result.cnt)) >= 200
        result.list.clouds.all.get(RandomMethod(result.cnt)) >= 0
        result.list.wind.speed.get(RandomMethod(result.cnt)) >= 0
        result.list.dt_txt.get(RandomMethod(result.cnt)).startsWith("2017")                    // test pass only during 2017 :)


        where:
        lonValue | latValue | name
        5.48     | 51.44    |"Eindhoven"
        -117.82  | 33.67    |"Irvine"
        -157.86  | 21.31    |"Honolulu"
        -8.61    | 41.15    |"Porto"

    }

    public int RandomMethod(int listsSize){
       return random.nextInt(listsSize)
    }
}
