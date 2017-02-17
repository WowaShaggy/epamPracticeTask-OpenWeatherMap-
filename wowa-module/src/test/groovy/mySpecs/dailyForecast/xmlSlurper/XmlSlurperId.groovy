package mySpecs.dailyForecast.xmlSlurper

import com.ihg.middleware.test.ExampleTestCase

class XmlSlurperId  extends ExampleTestCase{

    Random random = new Random();

    def "The user should get daily forecast by id of the city and number of days"() {
        def idValue = 5359777                                        //5359777   //5856195  //2735943 //2756253
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        //println cntValue                                           // В консольке будет видно количество
        def modeValue = "xml"

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

        def result = new XmlSlurper().parseText(response)


        then: "City's name and other list's data should be correct"
        then: "City's name, id, country and coordinates should be the same and other data should be correct"
        result.location.name == "Irvine"                                 //Irvine  //Honolulu //Porto  //Eindhoven
        result.location.country == "US"                                 //US      //US       //PT      //NL
        result.sun.@rise.toString().startsWith("2017")
        result.sun.@set.toString().startsWith("2017")
        result.forecast.time.@day.getAt(RandomMethod(cntValue)).toString().startsWith("2017")
        result.forecast.time.symbol.@number.getAt(RandomMethod(cntValue)).toInteger() >= 0
    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}
