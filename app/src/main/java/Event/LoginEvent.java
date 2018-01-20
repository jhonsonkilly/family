package Event;

/**
 * Created by mac on 2018/1/19.
 */

public class LoginEvent {

    public String phone;

    public String password;

    public LoginEvent(String phone, String password) {
        this.phone = phone;
        this.password = password;

    }
}
