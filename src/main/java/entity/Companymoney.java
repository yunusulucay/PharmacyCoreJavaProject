package entity;

import jakarta.persistence.*;

@Entity
public class Companymoney {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "companymoney")
    private Double companymoney;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getCompanymoney() {
        return companymoney;
    }

    public void setCompanymoney(Double companymoney) {
        this.companymoney = companymoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Companymoney that = (Companymoney) o;

        if (id != that.id) return false;
        if (companymoney != null ? !companymoney.equals(that.companymoney) : that.companymoney != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (companymoney != null ? companymoney.hashCode() : 0);
        return result;
    }
}
