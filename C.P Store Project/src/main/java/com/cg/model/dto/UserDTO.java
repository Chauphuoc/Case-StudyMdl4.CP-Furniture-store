package com.cg.model.dto;

import com.cg.model.User;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO implements Validator {

    private Long id;

    private String username;

    private String password;

    @Valid
    private RoleDTO role;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setRole(role.toRole())
                ;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if (username.length() == 0) {
            errors.rejectValue("username", "username.null", "Username is required");
        }
        else {
            if (username.length() <5 || username.length() > 35) {
                errors.rejectValue("username", "username.length", "The length of username must be between 5 and 50 characters");
            }
            else {
                if (!username.matches("^[\\w]+@([\\w-]+\\.)+[\\w-]{2,6}$")) {
                    errors.rejectValue("username", "username.matches", "The account must be a valid email");
                }
            }
        }

        if (password.length() == 0) {
            errors.rejectValue("password", "password.null", "Password is required");
        }
        else {
            if (password.length() > 35) {
                errors.rejectValue("password", "password.length", "The length of password must be less than 35 characters");
            }
        }
    }
}