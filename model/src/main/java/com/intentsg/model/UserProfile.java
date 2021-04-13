package com.intentsg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProfile {

    @NotBlank(message = "The name cannot be empty")
    @Size(min=1, max=50)
    @Pattern(regexp = "[A-Za-zЄ-ЯҐа-їґ -]*")
    private String userName;

    @NotEmpty
    @Size(min=4, max=32)
    private String password;
}
