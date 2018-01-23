package model;

import java.util.List;

/**
 * Created by mac on 18/1/23.
 */

public class QiangDanModel {

    public String total_count;
    public List<Items> items;
    public static class Items{
        public String id;
        public String category_1;
        public String category_2;
        public String dateline;
        public String uid;
        public String address;
        public String book_date;
        public String content;
        public String cate;
        public List<String> images;
        public String is_bid;
        public String book_time;

    }
}
