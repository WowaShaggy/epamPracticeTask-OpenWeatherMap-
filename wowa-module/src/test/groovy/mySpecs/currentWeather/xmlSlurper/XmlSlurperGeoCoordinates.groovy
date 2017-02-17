package mySpecs.currentWeather.xmlSlurper

import com.ihg.middleware.test.ExampleTestCase

class XmlSlurperGeoCoordinates extends ExampleTestCase{
    def "The user should check the data by geographic coordinates of the city"() {
        def lonValue = 5.48                                         //-117.82 //-157.86 //-8.61    //5.48
        def latValue = 51.44                                        //33.67   //21.31   //41.15    //51.44
        def modeValue = "xml"

        when: "I send a request with geographic coordinates of the city"
        def response = weatherApiHttpClient.send(
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
        result.city.country == "NL"                         //US      //US       //PT      //NL
        result.city.@id == 2756253                          //5359777   //5856195  //2735943 //2756253
        result.city.@name == "Eindhoven"                       //Irvine  //Honolulu //Porto   //Eindhoven
        result.temperature.@unit == "kelvin"
        result.temperature.@max.toDouble() > 200
        result.lastupdate.@value.toString().startsWith("2017")

    }
}