package org.onishkovvalery.vehicleservice.soap.schema;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"jsonPayload"})
@XmlRootElement(name = "getVehiclesResponse", namespace = "http://onishkovvalery.org/vehicleservice/soap")
public class GetVehiclesResponse {

    @XmlElement(namespace = "http://onishkovvalery.org/vehicleservice/soap", required = true)
    private String jsonPayload;

    public String getJsonPayload() {
        return jsonPayload;
    }

    public void setJsonPayload(String jsonPayload) {
        this.jsonPayload = jsonPayload;
    }
}
