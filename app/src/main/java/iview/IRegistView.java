package iview;

import com.androidyuan.frame.base.view.IBaseCommView;

/**
 * Created by mac on 18/1/24.
 */

public interface IRegistView extends IBaseCommView {

    public void getVertifyCodeRes(String msg);

    void confirmCode(String msg);
}
