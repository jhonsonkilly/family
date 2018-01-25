package iview;

import com.androidyuan.frame.base.view.IBaseCommView;

import model.GongJiangModel;
import model.GongJiangZanModel;
import model.QiangDanModel;

/**
 * Created by mac on 18/1/20.
 */

public interface IGongJiangView extends IBaseCommView  {

    void getGongJiangList(GongJiangModel model);

    void QiangDanSucess();
}
