package task6.domain;

public class ResultsProductCategory {
    int id;
    String name;

    public ResultsProductCategory(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ResultsProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
