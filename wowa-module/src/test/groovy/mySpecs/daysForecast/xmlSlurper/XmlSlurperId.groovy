package mySpecs.daysForecast.xmlSlurper

import com.ihg.middleware.test.ExampleTestCase

class XmlSlurperId extends ExampleTestCase{

    Random random = new Random();

    def "The user should check the data by id of the city"() {
        def idValue = 2756253                                   //5359777   //5856195  //2735943 //2756253
        def modeValue = "xml"

        when: "I send a request with id of the city"
        def response = fiveDayForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "id={id}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : idValue,
                                mode : modeValue
                        ]
        )

        def result = new XmlSlurper().parseText(response)

        then: "City's name and other list's data should be correct"
        result.location.name == "Eindhoven"                                 //Irvine  //Honolulu //Porto  //Eindhoven
        result.location.country == "NL"                                 //US      //US       //PT      //NL
        result.sun.@rise.toString().startsWith("2017")
        result.sun.@set.toString().startsWith("2017")
        result.forecast.time.@from.getAt(RandomMethod(result.forecast.time.size())).toString().startsWith("2017")
        result.forecast.time.@to.getAt(RandomMethod(result.forecast.time.size())).toString().startsWith("2017")
        result.forecast.time.symbol.@number.getAt(RandomMethod(result.forecast.time.size())).toInteger() >= 0
        result.forecast.time.windSpeed.@mps.getAt(RandomMethod(result.forecast.time.size())).toDouble() >= 0
    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}
