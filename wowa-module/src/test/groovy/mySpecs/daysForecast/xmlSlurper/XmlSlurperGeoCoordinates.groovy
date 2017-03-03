package mySpecs.daysForecast.xmlSlurper

import com.ihg.middleware.test.ExampleTestCase

class XmlSlurperGeoCoordinates extends ExampleTestCase {

    Random random = new Random();

    def "The user should get forecast list by geographic coordinates of the city"() {
        def modeValue = "xml"

        when: "I send a request with geographic coordinates of the city"
        def response = fiveDayForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lat : latValue,
                                lon : lonValue,
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
        lonValue | latValue | name        | country
        5.48     | 51.44    |"Eindhoven"  | "NL"
        -117.82  | 33.67    |"Irvine"     | "US"
        -157.86  | 21.31    |"Honolulu"   | "US"
        -8.61    | 41.15    |"Porto"      | "PT"


    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}
