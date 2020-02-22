import java.util.*;

public class TestDemo1 {
    /**
     * 时间复杂度：O（n+klog2k）
     * 空间复杂度：O（k）
     * @param array
     * @param k
     * @return
     */
    public static Integer[] findKNum(int[] array,int k){
        //1.建立大小为K的堆（小堆）
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            //在调整的时候起比较作用；
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);//o1.compareTo(o2)是小堆，o2.compareTo(o1)是大堆。
            }
        });
        for (int i = 0; i < array.length; i++) {
            if (minHeap.size()<k){
                minHeap.add(array[i]);
            }else{
                Integer top = minHeap.peek();
                if (top != null&&top < array[i]){
                    minHeap.poll();
                    minHeap.add(array[i]);
                }
            }
        }
        Integer[] integers = new Integer[k];
        for (int i = 0; i < k; i++) {
            Integer top = minHeap.peek();
            integers[i] = top;
            minHeap.poll();
        }
        return integers;
    }

    public List<String> topKFrequent(String[] words, int k) {
        //遍历words数组，将每个单词出现的次数对应起来
        Map<String,Integer> map = new HashMap<>();
        for (String s:words
             ) {
            //首先看当前字符串s,是否已经在map当中有对应关系
            if (map.get(s) == null){//警告没关系，alt+enter
                map.put(s,1);
            }else{
                map.put(s,map.get(s)+1);
            }
        }
        PriorityQueue<Map.Entry<String,Integer>> minHeap = new PriorityQueue<>(k,
                new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())){
                    return o2.getKey().compareTo(o1.getKey());
                }
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for (Map.Entry<String,Integer> entry : map.entrySet()
             ) {
            if (minHeap.size()<k){
                minHeap.add(entry);
            }else{
                Map.Entry<String,Integer> top = minHeap.peek();
                //频率相同
                if (top != null && top.getValue().equals(entry.getValue())){
                    //字母小的入
                    if (top.getKey().compareTo(entry.getKey())>0){
                        minHeap.poll();
                        minHeap.add(entry);
                    }
                }else{
                    if(top != null && top.getValue() < entry.getValue()){
                        minHeap.poll();
                        minHeap.add(entry);
                    }
                }
            }
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String pop = minHeap.peek().getKey();
            list.add(0,pop);
            minHeap.poll();
        }
        return list;
    }

    public static void main(String[] args) {
        int[] array = {12,4,6,17,9,51,21,10,45,31};
        Integer[] ret = findKNum(array,4);
        System.out.println(Arrays.toString(ret));
    }
}
