package ru.jarsoft.validation;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class BannerRequest {

    @NotNull
    private Integer categoryId;

    private Boolean deleted;

    @NotBlank
    @Size(min = 10, max = 10000)
    private String content;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;

    public int getCategoryId() {
        return categoryId;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public String getContent() {
        return content;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
}
