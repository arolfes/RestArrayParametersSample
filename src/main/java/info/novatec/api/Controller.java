package info.novatec.api;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.http.MediaType;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {

    @GetMapping(path = "arrays", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<String> getArrayQueryParameters(@RequestParam("array") String[] array) {

        return Arrays.asList(array);
    }

    @InitBinder
    private void initBinder(DataBinder binder) {
        binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor(null));
    }
}
