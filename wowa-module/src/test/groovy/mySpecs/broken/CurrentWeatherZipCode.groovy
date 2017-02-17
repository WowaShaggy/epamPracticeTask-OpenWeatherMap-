//package mySpecs.broken  // НЕ РАБОТАЕТ, А должен
//
//import com.ihg.middleware.test.ExampleTestCase
//import groovy.json.JsonSlurper
//
//class CurrentWeatherZipCode  extends ExampleTestCase{
//    def "The user should check the data by zip code of the city"() {
//        def zipValue = 94040
//        def countryCodeValue = "us"
//
//        when: "I send a request with zip code of the city"
//        def response = weatherApiHttpClient.send(
//                REQUEST_PARAMS_STRING : "zip={zip},{country}&appid=${APPid}",
//                REQUEST_PARAMS_VARIABLES :
//                        [
//                                zip : zipValue,
//                                country : countryCodeValue,
//                        ]
//        )
//        def slurper = new JsonSlurper()
//        def result = slurper.parseText(response)
//
//
//        then: "City's name and other data should be correct"
//        result.name == "Mountain View"
//    }
//}
