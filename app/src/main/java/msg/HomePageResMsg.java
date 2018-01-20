package msg;

import com.androidyuan.frame.base.protocal.http.LiveResponseMsg;

import model.HomePageModel;

/**
 * Created by mac on 18/1/20.
 */

public class HomePageResMsg extends LiveResponseMsg<HomePageModel> {


    public HomePageResMsg(int what) {
        super(what);
    }
}
