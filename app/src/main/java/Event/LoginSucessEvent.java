package Event;

import model.UserModel;

/**
 * Created by mac on 18/1/21.
 */

public class LoginSucessEvent {

    public UserModel model;

    public LoginSucessEvent(UserModel model) {
        this.model = model;

    }
}
