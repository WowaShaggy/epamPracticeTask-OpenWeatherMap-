package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper
import mySpecs.RequestBuilder

class DayForecastId extends ExampleTestCase{

    Random random = new Random();

    def "The user should check the data by id of the city"() {
        def mode = "json"

        when: "I send a request with id of the city"
        def response = daysForecastApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(id,mode,"${APPid}").build()

                /*REQUEST_PARAMS_STRING : "id={id}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : id,
                                mode : modeValue
                        ]*/
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "City's name and other list's data should be correct"
        result.city.name == name
        // tests below can be done through the cycle
        result.list.dt.get(RandomMethod(result.cnt)) > 0
        result.list.main.temp.get(RandomMethod(result.cnt)) >= 200
        result.list.clouds.all.get(RandomMethod(result.cnt)) >= 0
        result.list.wind.speed.get(RandomMethod(result.cnt)) >= 0
        result.list.dt_txt.get(RandomMethod(result.cnt)).startsWith("2017")                    // test pass only during 2017 :)

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
