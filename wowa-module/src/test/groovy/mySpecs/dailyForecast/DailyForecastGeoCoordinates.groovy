package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DailyForecastGeoCoordinates extends ExampleTestCase {

    Random random = new Random();

    def "The user should get daily forecast by geographic coordinates of the city and number of days"() {

        def latValue = 33.67                                         //33.67   //21.31   //41.15    //51.44
        def lonValue = -117.82                                       //-117.82 //-157.86 //-8.61    //5.48
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        // println cntValue                                          // В консольке будет видно количество
        def modeValue = "json"

        when: "I send a request with geographic coordinates of the city"
        def response = dailyForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lat : latValue,
                                lon : lonValue,
                                mode : modeValue,
                                cnt: cntValue
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "City's name and other list's data should be correct"
        result.city.name == "Irvine"                                 //Irvine  //Honolulu //Porto  //Eindhoven
        result.list.size() == result.cnt
        result.cnt == cntValue
        result.list.dt.get(RandomMethod(cntValue)) > 0
        result.list.temp.min.get(RandomMethod(cntValue)) >= 200         // возможно надо понизить, если вы ищите, например, Северный полюс
        result.list.clouds.get(RandomMethod(cntValue)) >= 0
        result.list.pressure.get(RandomMethod(cntValue)) >= 0

    }

    public int RandomMethod(int size){
        return random.nextInt(size)
    }
}
