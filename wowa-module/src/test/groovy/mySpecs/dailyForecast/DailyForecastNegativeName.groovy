package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class DailyForecastNegativeName extends ExampleTestCase{
    Random random = new Random();
    def "The user should send request with incorrect name of the city"() {
        def name = "Штормградttttttttttttt"
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        def mode = "json"

        when: "I send a request with incorrect name of the city"
        def response = dailyForecastApiHttpClientNew.sendAndVerifyResponseStatus(

                new RequestBuilder(name,mode,cntValue,"${APPid}").build(),502

                /*REQUEST_PARAMS_STRING : "q={location}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                mode : modeValue,
                                cnt: cntValue,
                        ],404*/
        )

        then: "Error message should appear"
        response

    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}