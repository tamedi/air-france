
package com.example.userregistration.model;
import com.example.userregistration.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotNull
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotNull
    @Min(value = 18, message = "Age should be greater than or equal to 18")
    private Integer age;

    @NotNull
    @Pattern(regexp = "(?i)France", message = "Only users from France can register")
    private String country;

    @Email
    private String email;

    private String phoneNumber;

    private Status status;


}

