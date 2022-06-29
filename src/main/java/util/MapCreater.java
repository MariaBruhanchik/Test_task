package util;

import java.util.HashMap;
import java.util.Map;

public class MapCreater {
    public static Map<String, Object> mapping(final String id, final String username, final String firstname,
                                              final String lastname, final String email, final String password,
                                              final String phone, final int status) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("username", username);
        parameters.put("firstname", firstname);
        parameters.put("lastname", lastname);
        parameters.put("email", email);
        parameters.put("password", password);
        parameters.put("phone", phone);
        parameters.put("status", status);
        return parameters;


    }

}
