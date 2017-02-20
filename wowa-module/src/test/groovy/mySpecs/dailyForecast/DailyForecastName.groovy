package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper


class DailyForecastName extends ExampleTestCase{
    Random random = new Random();

    def "The user should check the data by name of the city"() {
        def locationValue = "potru"                                    //Irvine  //Honolulu //potru //Eindhoven
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        println cntValue                                           // В консольке будет видно количество
        def modeValue = "json"

        when: "I send a request with the name of the city"
        def response = dailyForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "q={location}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                mode : modeValue,
                                cnt: cntValue,
                        ]
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "City's name, id, country and coordinates should be the same and other data should be correct"
        result.city.name == "Porto"                                      //Irvine  //Honolulu //Porto //Eindhoven
        result.city.country == "PT"                                        //US      //US       //PT      //NL
        result.city.id == 2735943                                           //5359777  //5856195  //2735943 //2756253
        result.city.coord.lat.toString().startsWith("41.1")                   //33.6   //21.3   //41.1    //51.4
        result.city.coord.lon.toString().startsWith("-8.6")                   //-117.8 //-157.8 //-8.6   //5.4
        result.list.size() == result.cnt
        result.cnt == cntValue
        result.list.dt.get(RandomMethod(cntValue)) > 1000000000
        result.list.temp.min.get(RandomMethod(cntValue)) >= 200         // возможно надо понизить, если вы ищите, например, Северный полюс
        result.list.clouds.get(RandomMethod(cntValue)) >= 0
        result.list.pressure.get(RandomMethod(cntValue)) >= 0
    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}