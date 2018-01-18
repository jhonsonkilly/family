package otto;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.otto</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class OttoBus extends Bus {

    private static OttoBus bus;

    public static OttoBus getInstance() {
        if (bus == null) {
            bus = new OttoBus();
        }
        return bus;
    }
}
