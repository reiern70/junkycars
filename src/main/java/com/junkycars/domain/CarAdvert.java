package com.junkycars.domain;


import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="CarAdvert")
@CompoundIndexes({
        @CompoundIndex(name = "CarAdvert_unique_id", def = "{'user': 1, firstRegistration: 1}", unique = true)
})

public class CarAdvert extends MongoBase {

    @Indexed
    private String title;
    private String description;
    @Indexed
    private String fuelType;
    @Indexed
    private Integer price;
    private Boolean isNew;
    @Indexed
    private Integer mileage;
    @Indexed
    private Date firstRegistration;
    @Indexed
    private String user;

    public CarAdvert() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType.getUuid();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Date getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(Date firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user.getUuid();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarAdvert carAdvert = (CarAdvert) o;

        if (!firstRegistration.equals(carAdvert.firstRegistration)) return false;
        return user.equals(carAdvert.user);

    }

    @Override
    public int hashCode() {
        int result = firstRegistration.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }
}
