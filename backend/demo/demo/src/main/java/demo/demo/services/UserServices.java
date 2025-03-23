package demo.demo.services;

import demo.demo.dto.loginDTO.LoginResponse;
import demo.demo.dto.loginDTO.RegisterPayload;

public abstract class UserServices {

    public abstract LoginResponse fetchUsers(String payload);

    public abstract void register(RegisterPayload payload);

    public abstract LoginResponse login(String user);
}
