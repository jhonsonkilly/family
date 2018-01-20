package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 18/1/20.
 */

public class ShopListModel implements Serializable {

    public List<Rotate> rotate;
    public List<Items> items;
    public List<Cates> cates;

    public class Rotate implements Serializable {
        public String url;
        public String img_url;

    }

    public class Items implements Serializable {
        public String title;
        public String cover;
        public String juli;
        public String city_name;
        public String link;

    }

    public class Cates implements Serializable {
        public String name;
        public String cover;
        public String id;
        public String link;
    }
}
