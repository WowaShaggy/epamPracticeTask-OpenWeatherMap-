package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper
import mySpecs.RequestBuilder


class DailyForecastName extends ExampleTestCase{
    Random random = new Random();

    def "The user should check the data by name of the city"() {
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        //println cntValue                                           // В консольке будет видно количество
        def mode = "json"

        when: "I send a request with the name of the city"
        def response = dailyForecastApiHttpClientNew.sendAndVerifyResponseStatus(

                new RequestBuilder(name, mode, cntValue,"${APPid}").build()

                /*REQUEST_PARAMS_STRING : "q={location}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : name,
                                mode : modeValue,
                                cnt: cntValue,
                        ]*/
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "City's name, id, country and coordinates should be the same and other data should be correct"
        result.city.name == name
        result.city.country == country
        result.city.id == id
        result.list.size() == result.cnt
        result.cnt == cntValue
        result.list.dt.get(RandomMethod(cntValue)) > 1000000000
        result.list.temp.min.get(RandomMethod(cntValue)) >= 200         // возможно надо понизить, если вы ищите, например, Северный полюс
        result.list.clouds.get(RandomMethod(cntValue)) >= 0
        result.list.pressure.get(RandomMethod(cntValue)) >= 0

        where:
        name          | id         | country
        "Eindhoven"   | 2756253    | "NL"
        "Irvine"      | 5359777    | "US"
        "Honolulu"    | 5856195    | "US"
        "Porto"       | 2735943    | "PT"
    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}