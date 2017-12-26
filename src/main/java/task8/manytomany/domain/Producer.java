package task8.manytomany.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCER")
public class Producer {
    @Id
    @Column(name = "PRODUCER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producer_id_gen")
    @SequenceGenerator(name = "producer_id_gen", sequenceName = "PRODUCER_ID_SEQ")
    private int producerId;
    @Column(nullable = false, unique = true)
    private String name;
    private String address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_AND_PRODUCER",
            joinColumns = @JoinColumn(name = "PRODUCER_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private Set<Product> productSet = new HashSet<>();


    public Producer(){}

    public Producer(String name,String address, Set<Product> productSet) {
        this.name = name;
        this.address = address;
        this.productSet = productSet;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }
}
