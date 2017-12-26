package task6.domain;

public class ResultsProducer {
    int id;
    String name;
    String address;

    public ResultsProducer(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
