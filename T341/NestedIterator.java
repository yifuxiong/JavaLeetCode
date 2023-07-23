//package T341;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class NestedIterator implements Iterator<Integer> {
//    private Iterator<Integer> iterator;
//
//    public NestedIterator(List<NestedInteger> nestedList) {
//        List<Integer> list = new ArrayList<>();
//        add(list, nestedList);
//        iterator = list.iterator();
//    }
//
//    public void add(List<Integer> list, List<NestedInteger> nestedList) {
//        for (NestedInteger nestedInteger : nestedList) {
//            if (nestedInteger.isInteger()) {
//                list.add(nestedInteger.getInteger());
//            } else {
//                add(list, nestedInteger.getList());
//            }
//        }
//    }
//
//    @Override
//    public Integer next() {
//        return iterator.next();
//    }
//
//    @Override
//    public boolean hasNext() {
//        return iterator.hasNext();
//    }
//
//}
