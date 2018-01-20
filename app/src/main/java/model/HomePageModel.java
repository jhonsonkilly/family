package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 18/1/13.
 */

public class HomePageModel {

    public List<String> rotate;
    public List<Cates> cates;
    public List<PageView> reRotate = new ArrayList<>();

    public static class Cates {
        public String id;
        public String name;
        public String cover;
        public String link;
    }

    public static class PageView {
        public String url;
    }

    public void convertMes(List<String> rotate) {

        for (String id : rotate) {
            PageView view = new PageView();
            view.url = id;
            reRotate.add(view);
        }
    }


}
