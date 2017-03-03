package mySpecs.currentWeather.xmlSlurper

import com.ihg.middleware.test.ExampleTestCase

class XmlSlurperGeoCoordinates extends ExampleTestCase{
    def "The user should check the data by geographic coordinates of the city"() {
        def modeValue = "xml"

        when: "I send a request with geographic coordinates of the city"
        def response = weatherApiHttpClient.sendAndVerifyResponseStatus(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lon : lonValue,
                                lat : latValue,
                                mode : modeValue,
                        ]
        )

        def result = new XmlSlurper().parseText(response)


        then: "City's name and other data should be correct"
        result.city.country == country
        result.city.@id == id
        result.city.@name == name
        result.temperature.@unit == "kelvin"
        result.temperature.@max.toDouble() > 200
        result.lastupdate.@value.toString().startsWith("2017")


        where:
        lonValue | latValue | name         | id         | country
        5.48     | 51.44    |"Eindhoven"   | 2756253    | "NL"
        -117.82  | 33.67    |"Irvine"      | 5359777    | "US"
        -157.86  | 21.31    |"Honolulu"    | 5856195    | "US"
        -8.61    | 41.15    |"Porto"       | 2735943    | "PT"

    }
}