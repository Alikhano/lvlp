package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BandListImpl {

    public static void main(String[] args) {
        BandList<String> list = new BandList();
        list.add("4");
        list.add("link");
        list.add("word");
        list.add("hello");
        list.add("test");
        list.add("band");
        list.remove(4);
        list.remove("test");
        list.get(1);

        System.out.println();

        for (String s : list) {
            System.out.println(s);
        }

        List<String> sList = new ArrayList<>();

        for (String s : list) {
            sList.add(s);
        }

        List<String> sortedList = sList.stream().sorted().collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }
}
