//package mySpecs.broken       // Не работает а должен
//
//import com.ihg.middleware.test.ExampleTestCase
//import groovy.json.JsonSlurper
//
//class CurrentWeatherCitiesInCycle extends ExampleTestCase{
//    def "do"() {
//        def lonValue = 37.5
//        def latValue = 55.5
//        def cntValue = 10
//
//        when: "some"
//        def response = searchRegionsApiHttpClient.send(
//                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&cnt={cnt}&appid=${APPid}",
//                REQUEST_PARAMS_VARIABLES :
//                        [
//                                lon : lonValue,
//                                lat : latValue,
//                                cnt : cntValue,
//                        ]
//        )
//        def slurper = new JsonSlurper()
//        def result = slurper.parseText(response)
//
//        then: "thing"
//        result.count == 10
//    }
//}