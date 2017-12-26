package task8.manytomany.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private int productId;
    @Column(name = "NAME", length = 50, nullable = false, unique = true)
    private String name;
    @Column(name = "WEIGHT")
    private double weight;

    @ManyToMany(mappedBy = "productSet")
    private Set<Producer> producerSet = new HashSet<>();

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    public Set<Producer> getProducerSet() {
        return producerSet;
    }

    public void setProducerSet(Set<Producer> producerSet) {
        this.producerSet = producerSet;
    }
}
