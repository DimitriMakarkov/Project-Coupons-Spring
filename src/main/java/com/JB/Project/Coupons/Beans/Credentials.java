package com.JB.Project.Coupons.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    public String email;
    public String password;
    public UserType userType;
}
