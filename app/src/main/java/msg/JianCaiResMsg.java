package msg;

import com.androidyuan.frame.base.protocal.http.LiveResponseMsg;

import model.ShopListModel;

/**
 * Created by mac on 18/1/20.
 */

public class JianCaiResMsg extends LiveResponseMsg<ShopListModel> {
    public JianCaiResMsg(int what) {
        super(what);
    }
    
}
