package ua.com.jcoh.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.jcoh.entity.User;
import ua.com.jcoh.service.UserService;

@Component
public class UserValidator implements Validator {
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;
        if (userService.findByName(user.getUsername()) != null) {
            errors.rejectValue("username", "error","That name is already exist");
        }

        if (userService.findByCode(user.getCode()) != null) {
            errors.rejectValue("code", "error","That code is already exist");
        }
    }
}
