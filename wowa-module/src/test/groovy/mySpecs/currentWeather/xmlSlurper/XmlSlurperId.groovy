package mySpecs.currentWeather.xmlSlurper

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class XmlSlurperId extends ExampleTestCase{
    def "The user should check the data by id of the city"() {
        def mode = "xml"

        when: "I send a request with id of the city"
        def response = currentWeatherApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(id, mode,"${APPid}").build()

                /*REQUEST_PARAMS_STRING : "id={id}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : id,
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
            id         | country |  name
            2756253    | "NL"    |  "Eindhoven"
            5359777    | "US"    |  "Irvine"
            5856195    | "US"    |  "Honolulu"
            2735943    | "PT"    |  "Porto"

    }
}
