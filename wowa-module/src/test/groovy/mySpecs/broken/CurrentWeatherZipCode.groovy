//package mySpecs.broken
//
//import com.ihg.middleware.test.ExampleTestCase
//import groovy.json.JsonSlurper
//
//// НЕ РАБОТАЕТ, А должен
//class CurrentWeatherZipCode  extends ExampleTestCase{
//    def "The user should check the data by zip code of the city"() {
//        def zipValue = 96701                       //94040 не работает как и 10011, 33024, 10451, 94043 // короче это проблема с их стороны, пусть нафиг идут
//
//        when: "I send a request with zip code of the city"
//        def response = weatherApiHttpClient.send(
//                REQUEST_PARAMS_STRING : "zip={zip},us&appid=${APPid}",
//                REQUEST_PARAMS_VARIABLES :
//                        [
//                                zip : zipValue,
//                        ]
//        )
//
//        def slurper = new JsonSlurper()
//        def result = slurper.parseText(response)
//
//
//        then: "City's name and other data should be correct"
//        result.name == "Mountain View"
//    }
//}
