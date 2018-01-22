package msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;

import config.UrlConfig;

/**
 * Created by mac on 18/1/23.
 */

public class QiangDanReqMsg extends RequestMsg {

    String lat;
    String lng;
    int t;
    int page;
    int per_page;

    //经纬度,POS,页数,条数
    public QiangDanReqMsg(String x, String y, int position, int page, int per_page) {
        lng = x;
        lat = y;
        t = position;
        this.page = page;
        this.per_page = per_page;
    }

    @Override
    public String getUrl() {
        return UrlConfig.QiangDanUrl() +
                "&lng=" + lng +
                "& lat=" + lat +
                "& t=" + t +
                "& page=" + page +
                "& per_page=" + per_page;
    }
}
