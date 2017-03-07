package mySpecs.currentWeather.xmlSlurper

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class XmlSlurperName extends ExampleTestCase{
    def "The user should check the data by name of the city"() {
        def mode = "xml"

        when: "I send a request with the name of the city"
        def response = currentWeatherApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(name, mode,"${APPid}").build()

                /*REQUEST_PARAMS_STRING : "q={location}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : name,
                                mode : modeValue,
                        ]*/
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
         name         | id         | country
        "Eindhoven"   | 2756253    | "NL"
        "Irvine"      | 5359777    | "US"
        "Honolulu"    | 5856195    | "US"
        "Porto"       | 2735943    | "PT"

    }
}