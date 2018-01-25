package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2018/1/25.
 */

public class GongJiangModel {

    public String total_count;
    public List<Items> items;

    public static class Items implements Serializable {
        public String ding_count;
        public List<String> images;
        public String comment_count;
        public String linkurl;
        public String is_ding;
        public String dateline;
        public String juli;
        public String title;
        public String id;
        public User user;

        public static class User implements Serializable {
            public String name;
        }


    }

}
