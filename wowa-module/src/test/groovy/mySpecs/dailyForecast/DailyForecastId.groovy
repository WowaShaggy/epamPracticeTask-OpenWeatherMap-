package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DailyForecastId extends ExampleTestCase{

    Random random = new Random();

    def "The user should get daily forecast by id of the city and number of days"() {
        def idValue = 5359777                                        //5359777   //5856195  //2735943 //2756253
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        //println cntValue                                           // В консольке будет видно количество
        def modeValue = "json"

        when: "I send a request with id of the city"
        def response = dailyForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "id={id}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : idValue,
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

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}
