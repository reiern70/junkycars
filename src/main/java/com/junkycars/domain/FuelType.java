package com.junkycars.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FuelType")
public class FuelType extends MongoBase {

    @Indexed(unique = true)
    private String name;
    private String description;

    public FuelType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FuelType fuelType = (FuelType) o;

        return name != null ? name.equals(fuelType.name) : fuelType.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FuelType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
