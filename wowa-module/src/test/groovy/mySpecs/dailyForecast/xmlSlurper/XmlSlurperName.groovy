package mySpecs.dailyForecast.xmlSlurper

import com.ihg.middleware.test.ExampleTestCase

class XmlSlurperName extends ExampleTestCase{
    Random random = new Random();

    def "The user should check the data by name of the city"() {
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        //println cntValue                                           // В консольке будет видно количество
        def modeValue = "xml"

        when: "I send a request with the name of the city"
        def response = dailyForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "q={location}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : name,
                                mode : modeValue,
                                cnt: cntValue
                        ]
        )

        def result = new XmlSlurper().parseText(response)

        then: "City's name, id, country and coordinates should be the same and other data should be correct"
        result.location.name == name
        result.location.country ==  country
        result.sun.@rise.toString().startsWith("2017")
        result.sun.@set.toString().startsWith("2017")
        result.forecast.time.@day.getAt(RandomMethod(cntValue)).toString().startsWith("2017")
        result.forecast.time.symbol.@number.getAt(RandomMethod(cntValue)).toInteger() >= 0

        where:
        name          | country
        "Eindhoven"   | "NL"
        "Irvine"      | "US"
        "Honolulu"    | "US"
        "Porto"       | "PT"
    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}