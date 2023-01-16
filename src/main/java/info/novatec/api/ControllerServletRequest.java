package info.novatec.api;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ControllerServletRequest {

    @GetMapping(path = "servletRequestArrays", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<String> getArrayQueryParameters(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String[]> queryParameters = new HashMap<>();
        String queryString = request.getQueryString();
        if (StringUtils.isNotEmpty(queryString)) {
            queryString = URLDecoder.decode(queryString, StandardCharsets.UTF_8.toString());
            String[] parameters = queryString.split("&");
            for (String parameter : parameters) {
                String[] keyValuePair = parameter.split("=");
                String[] values = queryParameters.get(keyValuePair[0]);
                //length is one if no value is available.
                values = keyValuePair.length == 1 ? ArrayUtils.add(values, "") :
                        ArrayUtils.addAll(values, keyValuePair[1]);
                queryParameters.put(keyValuePair[0], values);
            }
        }
        return Arrays.asList(queryParameters.get("array"));
    }
}
