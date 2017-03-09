package mySpecs.dailyForecast

import com.ihg.middleware.test.ExampleTestCase
import mySpecs.RequestBuilder

class DailyForecastNegativeId extends ExampleTestCase{
    Random random = new Random();
    def "The user should send request with incorrect id of the city"() {
        def id = 0
        def cntValue = random.nextInt(16)+1                          // Рандомное количество дней, от 1 до 16
        def mode = "json"

        when: "I send a request with incorrect id of the city"
        def response = dailyForecastApiHttpClientNew.sendAndVerifyResponseStatus(

                new RequestBuilder(id,mode,cntValue,"${APPid}").build(),502         //400

               /* REQUEST_PARAMS_STRING : "id={id}&cnt={cnt}&mode={mode}&appid=${APPid}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                id : idValue,
                                mode : modeValue,
                                cnt: cntValue,
                        ],400*/
        )

        then: "Error message should appear"
        response
    }

    public int RandomMethod(int listsSize){
        return random.nextInt(listsSize)
    }
}