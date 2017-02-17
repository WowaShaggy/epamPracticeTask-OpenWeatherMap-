//// Не работает, а должен
//
//import com.ihg.middleware.test.ExampleTestCase
//import groovy.json.JsonSlurper
//
//class CurrentWeatherRectangleZone extends ExampleTestCase{
//    def "one"() {
//        def lonleftV = 12
//        def latbottomV = 32
//        def lonrightV = 15
//        def lattopV =37
//        def zoomV = 10
//        def modeV = "json"
//
//
//        when: "two"
//        def response = rectangleZoneApiHttpClient.send(
//                REQUEST_PARAMS_STRING : "bbox={lonleft},{latbottom},{lonright},{lattop},{zoom}&mode={mode}&appid=${APPid}",
//                REQUEST_PARAMS_VARIABLES :
//                        [
//                                lonleft : lonleftV,
//                                latbottom : latbottomV,
//                                lonright : lonrightV,
//                                lattop : lattopV,
//                                zoom : zoomV,
//                                mode : modeV
//                        ]
//        )
//        def slurper = new JsonSlurper()
//        def result = slurper.parseText(response)
//
//
//        then: "three"
//        result.cod == "200"
//    }
//}
