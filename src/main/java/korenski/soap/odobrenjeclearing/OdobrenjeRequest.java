//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.20 at 02:03:02 PM CEST 
//


package korenski.soap.odobrenjeclearing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zahtev" type="{http://korenski/soap/odobrenjeclearing}OdobrenjeClearing"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "zahtev"
})
@XmlRootElement(name = "odobrenjeRequest")
public class OdobrenjeRequest {

    @XmlElement(required = true)
    protected OdobrenjeClearing zahtev;

    /**
     * Gets the value of the zahtev property.
     * 
     * @return
     *     possible object is
     *     {@link OdobrenjeClearing }
     *     
     */
    public OdobrenjeClearing getZahtev() {
        return zahtev;
    }

    /**
     * Sets the value of the zahtev property.
     * 
     * @param value
     *     allowed object is
     *     {@link OdobrenjeClearing }
     *     
     */
    public void setZahtev(OdobrenjeClearing value) {
        this.zahtev = value;
    }

}
