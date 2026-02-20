package org.onishkovvalery.vehicleservice.soap.schema;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"fuelType", "page", "size"})
@XmlRootElement(name = "getVehiclesRequest", namespace = "http://onishkovvalery.org/vehicleservice/soap")
public class GetVehiclesRequest {

    @XmlElement(namespace = "http://onishkovvalery.org/vehicleservice/soap")
    private String fuelType;

    @XmlElement(namespace = "http://onishkovvalery.org/vehicleservice/soap")
    private Integer page;

    @XmlElement(namespace = "http://onishkovvalery.org/vehicleservice/soap")
    private Integer size;

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
