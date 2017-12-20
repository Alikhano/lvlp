package task7.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCER")
public class Producer {

    @Id
    @Column(name = "PRODUCER_ID")
    private int id;
    @Column(name = "NAME", length = 50, nullable = false, unique = true)
    private String name;
    @Column(name = "ADDRESS", length = 150, nullable = false)
    private String address;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }
}
