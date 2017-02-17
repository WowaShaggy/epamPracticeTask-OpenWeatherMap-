package mySpecs.currentWeather.xmlSlurper

import com.ihg.middleware.test.ExampleTestCase

class XmlSlurperName extends ExampleTestCase{
    def "The user should check the data by name of the city"() {
        def locationValue = "Eindhoven"                                   //Irvine  //Honolulu //Porto   //Eindhoven
        def modeValue = "xml"

        when: "I send a request with the name of the city"
        def response = weatherApiHttpClient.send(
                REQUEST_PARAMS_STRING : "q={location}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                mode : modeValue,
                        ]
        )

        def result = new XmlSlurper().parseText(response)


        then: "City's name and other data should be correct"
        result.city.country == "NL"                         //US      //US       //PT      //NL
        result.city.@id == 2756253                          //5359777   //5856195  //2735943 //2756253
        result.city.@name == "Eindhoven"                       //Irvine  //Honolulu //Porto   //Eindhoven
        result.temperature.@unit == "kelvin"
        result.temperature.@max.toDouble() > 200
        result.lastupdate.@value.toString().startsWith("2017")

    }
}