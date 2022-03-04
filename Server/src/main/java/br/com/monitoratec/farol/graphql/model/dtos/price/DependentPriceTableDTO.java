package br.com.monitoratec.farol.graphql.model.dtos.price;

import java.util.List;
import java.util.Objects;

public class DependentPriceTableDTO {
    private long id;
    private String name;
    private List<PriceTableAgeRangeDTO> ageRanges;

    public DependentPriceTableDTO() {
    }

    public DependentPriceTableDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DependentPriceTableDTO(long id, String name, List<PriceTableAgeRangeDTO> priceTableAgeRangeDTO) {
        this.id = id;
        this.name = name;
        this.ageRanges = priceTableAgeRangeDTO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PriceTableAgeRangeDTO> getAgeRanges() {
        return ageRanges;
    }

    public void setAgeRanges(List<PriceTableAgeRangeDTO> ageRanges) {
        this.ageRanges = ageRanges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DependentPriceTableDTO that = (DependentPriceTableDTO) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(ageRanges, that.ageRanges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ageRanges);
    }

    @Override
    public String toString() {
        return "PriceTableDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ageRanges=" + ageRanges +
                '}';
    }
}
