package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class DailyForecastId extends ExampleTestCase{

    Random random = new Random();

    def "The user should get daily forecast by id of the city and number of days"() {
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        //println cntValue                                           // В консольке будет видно количество
        def modeValue = "json"

        when: "I send a request with id of the city"
        def response = dailyForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "id={id}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : id,
                                mode : modeValue,
                                cnt: cntValue
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)


        then: "City's name and other list's data should be correct"
        result.city.name == name
        result.list.size() == result.cnt
        result.cnt == cntValue
        result.list.dt.get(RandomMethod(cntValue)) > 0
        result.list.temp.min.get(RandomMethod(cntValue)) >= 200         // возможно надо понизить, если вы ищите, например, Северный полюс
        result.list.clouds.get(RandomMethod(cntValue)) >= 0
        result.list.pressure.get(RandomMethod(cntValue)) >= 0

        where:
        id         |  name
        2756253    |  "Eindhoven"
        5359777    |  "Irvine"
        5856195    |  "Honolulu"
        2735943    |  "Porto"
    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}
