package org.onishkovvalery.vehicleservice.soap.schema;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"id", "distanceTravelled"})
@XmlRootElement(name = "updateVehicleDistanceRequest", namespace = "http://onishkovvalery.org/vehicleservice/soap")
public class UpdateVehicleDistanceRequest {

    @XmlElement(namespace = "http://onishkovvalery.org/vehicleservice/soap", required = true)
    private Long id;

    @XmlElement(namespace = "http://onishkovvalery.org/vehicleservice/soap")
    private Integer distanceTravelled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(Integer distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }
}
