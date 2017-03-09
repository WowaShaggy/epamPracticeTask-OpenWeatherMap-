package mySpecs

class RequestBuilder {

    public Map<String, String> REQUEST_PARAMS_VARIABLES;
    public Map<String, String> REQUEST_HEADERS;
    public Map<String, String> REQUEST_METHOD;
    public Map<String, String> REQUEST_BODY;
    public String REQUEST_PARAMS_STRING = "";

    RequestBuilder(double lon, double lat, String key) {
        REQUEST_PARAMS_VARIABLES = new HashMap<>();
        REQUEST_PARAMS_VARIABLES.put("lon", lon);
        REQUEST_PARAMS_VARIABLES.put("lat", lat);
        REQUEST_PARAMS_VARIABLES.put("appid", key);
    }

    RequestBuilder(int id, String key) {
        REQUEST_PARAMS_VARIABLES = new HashMap<>();
        REQUEST_PARAMS_VARIABLES.put("id", id);
        REQUEST_PARAMS_VARIABLES.put("appid", key);
    }

    RequestBuilder(String name, String key) {
        REQUEST_PARAMS_VARIABLES = new HashMap<>();
        REQUEST_PARAMS_VARIABLES.put("q", name);
        REQUEST_PARAMS_VARIABLES.put("appid", key);
    }

    RequestBuilder(String key) {
        REQUEST_PARAMS_VARIABLES = new HashMap<>();
        REQUEST_PARAMS_VARIABLES.put("appid", key);
    }

    RequestBuilder(String name,String mode, String key) {
        REQUEST_PARAMS_VARIABLES = new HashMap<>();
        REQUEST_PARAMS_VARIABLES.put("q", name);
        REQUEST_PARAMS_VARIABLES.put("mode", mode);
        REQUEST_PARAMS_VARIABLES.put("appid", key);
    }

    RequestBuilder(double lon, double lat, String mode, String key) {
        REQUEST_PARAMS_VARIABLES = new HashMap<>();
        REQUEST_PARAMS_VARIABLES.put("lon", lon);
        REQUEST_PARAMS_VARIABLES.put("lat", lat);
        REQUEST_PARAMS_VARIABLES.put("mode", mode);
        REQUEST_PARAMS_VARIABLES.put("appid", key);
    }

    RequestBuilder(int id, String mode, String key) {
        REQUEST_PARAMS_VARIABLES = new HashMap<>();
        REQUEST_PARAMS_VARIABLES.put("id", id);
        REQUEST_PARAMS_VARIABLES.put("mode", mode);
        REQUEST_PARAMS_VARIABLES.put("appid", key);
    }

    RequestBuilder(double lon, double lat, String mode, int cnt, String key) {
        REQUEST_PARAMS_VARIABLES = new HashMap<>();
        REQUEST_PARAMS_VARIABLES.put("lon", lon);
        REQUEST_PARAMS_VARIABLES.put("lat", lat);
        REQUEST_PARAMS_VARIABLES.put("mode", mode);
        REQUEST_PARAMS_VARIABLES.put("cnt", cnt);
        REQUEST_PARAMS_VARIABLES.put("appid", key);
    }

    RequestBuilder(int id, String mode, int cnt ,String key) {
        REQUEST_PARAMS_VARIABLES = new HashMap<>();
        REQUEST_PARAMS_VARIABLES.put("id", id);
        REQUEST_PARAMS_VARIABLES.put("mode", mode);
        REQUEST_PARAMS_VARIABLES.put("cnt", cnt);
        REQUEST_PARAMS_VARIABLES.put("appid", key);
    }

    RequestBuilder(String name, String mode, int cnt ,String key) {
        REQUEST_PARAMS_VARIABLES = new HashMap<>();
        REQUEST_PARAMS_VARIABLES.put("q", name);
        REQUEST_PARAMS_VARIABLES.put("mode", mode);
        REQUEST_PARAMS_VARIABLES.put("cnt", cnt);
        REQUEST_PARAMS_VARIABLES.put("appid", key);
    }




    public build() {
        for (Map.Entry<String, String> entry : REQUEST_PARAMS_VARIABLES.entrySet())
        {
            REQUEST_PARAMS_STRING += (entry.getKey()+"="+entry.getValue()+"&");
        }
        this;
    }
}