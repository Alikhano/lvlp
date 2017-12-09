package task1;

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
    }
}
