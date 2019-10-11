package com.jy.springboot.form;

import com.jy.springboot.domain.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Pattern;

/**
 * Created by limi on 2017/8/22.
 */
public class UserForm {

    public static final String PHONE_REG = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";


    @NotBlank(message = "Cannot be null.")
    private String username;
    @Length(min = 6, message = "Password Must Have 6 bits.")
    private String password;
    @Pattern(regexp = PHONE_REG, message = "Please Input Correct Phone Number.")
    private String phone;
    @Email(message = "It's not a valid Email.")
    private String email;
    @NotBlank(message = "Cannot be null.")
    private String confirmPassword;

    public UserForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String  getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public boolean confirmPassword() {
        if (this.password.equals(this.confirmPassword)) {
            return true;
        }
        return false;
    }

    public User convertToUser() {
        User user = new UserFormConvert().convert(this);
        return user;
    }


    private class UserFormConvert implements FormConvert<UserForm,User> {

        @Override
        public User convert(UserForm userForm) {
            User user = new User();
            BeanUtils.copyProperties(userForm,user);
            return user;
        }
    }

}
