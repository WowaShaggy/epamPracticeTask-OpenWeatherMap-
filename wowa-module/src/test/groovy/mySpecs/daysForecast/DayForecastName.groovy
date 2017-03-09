package mySpecs.daysForecast

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper
import mySpecs.RequestBuilder

class DayForecastName extends ExampleTestCase{
    def "The user should check the data by name of the city"() {
        def mode = "json"

        when: "I send a request with the name of the city"
        def response = daysForecastApiHttpClient.sendAndVerifyResponseStatus(

                new RequestBuilder(name,mode,"${APPid}").build()

                /*REQUEST_PARAMS_STRING : "q={location}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : name,
                                mode : modeValue
                        ]*/
        )
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)

        then: "City's name, id, country and coordinates should be the same, and list's size should be 36"
        result.city.name == name
        result.city.country == country
        result.city.id == id
        result.list.size() == result.cnt

        where:
        name          | id         | country
        "Eindhoven"   | 2756253    | "NL"
        "Irvine"      | 5359777    | "US"
        "Honolulu"    | 5856195    | "US"
        "Porto"       | 2735943    | "PT"
    }
}
