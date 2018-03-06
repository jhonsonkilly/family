package fragment;

/**
 * Created by mac on 2018/2/27.
 */

public class NameRepository implements Container {
    public String names[] = {"Robert", "John", "Julie", "Lora"};

    @Override
    public MineFragment.Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements MineFragment.Iterator {

        int index;

        @Override
        public boolean hasNext() {
            if (index < names.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}
