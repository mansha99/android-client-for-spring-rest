package mansha99.com.restclient.dto;

import java.util.Map;

import mansha99.com.restclient.models.Contact;

/**
 * Created by shivay on 10/29/2016.
 */
public class SaveContactDTO {
    private Map<String,String> errors;
    private String status;
    private String message;
    private Contact model;
    private String valid;

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Contact getModel() {
        return model;
    }

    public void setModel(Contact model) {
        this.model = model;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
