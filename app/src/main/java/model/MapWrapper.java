package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.model</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class MapWrapper implements Serializable {

    private HashMap mMap;

    public MapWrapper setMap(HashMap map) {
         mMap = map;
        return this;
    }

    public HashMap getMap() {
        return mMap;
    }
}
