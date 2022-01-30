package sam.henhaochi.authservice.utilities;

import com.google.gson.Gson;

public class Json {
    public static String toJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
