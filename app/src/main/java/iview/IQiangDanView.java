package iview;

import com.androidyuan.frame.base.view.IBaseCommView;

import model.HomePageModel;
import model.QiangDanModel;

/**
 * Created by mac on 18/1/20.
 */

public interface IQiangDanView extends IBaseCommView  {

    void getQiangDanList(QiangDanModel model);
}
