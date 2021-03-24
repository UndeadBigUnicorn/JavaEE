package com.undeadbigunicorn.demo.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class UserDto {
    @NotNull(message = "Login cannot be empty")
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Login can contain only letters, numbers and underscores")
    private String login;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, max = 40)
    private String password;
}