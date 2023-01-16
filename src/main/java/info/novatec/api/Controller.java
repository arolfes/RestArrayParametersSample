package info.novatec.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {

    @GetMapping(path = "arrays", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<String> getArrayQueryParameters(@RequestParam("array") String[] array) {

        return Arrays.asList(array);
    }
}
