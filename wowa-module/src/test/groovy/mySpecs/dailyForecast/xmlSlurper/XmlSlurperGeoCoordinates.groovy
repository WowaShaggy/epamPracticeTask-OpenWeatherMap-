package mySpecs.dailyForecast.xmlSlurper

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class XmlSlurperGeoCoordinates extends ExampleTestCase {

    Random random = new Random();

    def "The user should get daily forecast by geographic coordinates of the city and number of days"() {

        def cnt = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        // println cnt                                          // В консольке будет видно количество
        def mode = "xml"

        when: "I send a request with geographic coordinates of the city"
        def response = dailyForecastApiHttpClientNew.sendAndVerifyResponseStatus(

                new RequestBuilder(lonValue,latValue, mode, cnt,"${APPid}").build()

                /*REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lat : latValue,
                                lon : lonValue,
                                mode : modeValue,
                                cnt: cntValue
                        ]*/
        )

        def result = new XmlSlurper().parseText(response)

        then: "City's name and other list's data should be correct"
        result.location.name == name
        result.location.country == country
        result.sun.@rise.toString().startsWith("2017")
        result.sun.@set.toString().startsWith("2017")
        result.forecast.time.@day.getAt(RandomMethod(cnt)).toString().startsWith("2017")
        result.forecast.time.symbol.@number.getAt(RandomMethod(cnt)).toInteger() >= 0

        where:
        lonValue | latValue | name        | country
        5.48     | 51.44    |"Eindhoven"  | "NL"
        -117.82  | 33.67    |"Irvine"     | "US"
        -157.86  | 21.31    |"Honolulu"   | "US"
        -8.61    | 41.15    |"Porto"      | "PT"

    }

    public int RandomMethod(int size){
        return random.nextInt(size)
    }
}