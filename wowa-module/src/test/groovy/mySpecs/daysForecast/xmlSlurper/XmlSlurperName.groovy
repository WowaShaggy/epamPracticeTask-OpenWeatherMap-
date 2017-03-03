package mySpecs.daysForecast.xmlSlurper

import com.ihg.middleware.test.ExampleTestCase

class XmlSlurperName extends ExampleTestCase{
    Random random = new Random();

    def "The user should check the data by name of the city"() {
        def modeValue = "xml"

        when: "I send a request with the name of the city"
        def response = fiveDayForecastApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "q={location}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : name,
                                mode : modeValue
                        ]
        )

        def result = new XmlSlurper().parseText(response)

        then: "City's name and other list's data should be correct"
        result.location.name == name
        result.location.country == country
        result.sun.@rise.toString().startsWith("2017")
        result.sun.@set.toString().startsWith("2017")
        result.forecast.time.@from.getAt(RandomMethod(result.forecast.time.size())).toString().startsWith("2017")
        result.forecast.time.@to.getAt(RandomMethod(result.forecast.time.size())).toString().startsWith("2017")
        result.forecast.time.symbol.@number.getAt(RandomMethod(result.forecast.time.size())).toInteger() >= 0
        result.forecast.time.windSpeed.@mps.getAt(RandomMethod(result.forecast.time.size())).toDouble() >= 0


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
