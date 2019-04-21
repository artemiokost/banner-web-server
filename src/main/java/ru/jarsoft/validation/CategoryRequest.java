package ru.jarsoft.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryRequest {

    private Boolean deleted;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 2, max = 100)
    private String reqName;

    public Boolean isDeleted() {
        return deleted;
    }
    public String getName() {
        return name;
    }
    public String getReqName() {
        return reqName;
    }
}
