//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.25 at 10:38:26 PM CEST 
//


package korenski.model.nalog_za_prenos;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/nalog_za_prenos}Nalog_za_prenos" maxOccurs="unbounded"/>
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
    "nalogZaPrenos"
})
@XmlRootElement(name = "Nalozi")
public class Nalozi {

    @XmlElement(name = "Nalog_za_prenos", required = true)
    protected List<NalogZaPrenos> nalogZaPrenos;

    /**
     * Gets the value of the nalogZaPrenos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nalogZaPrenos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNalogZaPrenos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NalogZaPrenos }
     * 
     * 
     */
    public List<NalogZaPrenos> getNalogZaPrenos() {
        if (nalogZaPrenos == null) {
            nalogZaPrenos = new ArrayList<NalogZaPrenos>();
        }
        return this.nalogZaPrenos;
    }

}
