//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.20 at 02:03:02 PM CEST 
//


package korenski.soap.odobrenjeclearing;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Poruka complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Poruka">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Banka" type="{http://korenski/soap/odobrenjeclearing}TBanka"/>
 *         &lt;element name="Id_poruke_naloga">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="MT102"/>
 *               &lt;enumeration value="MT103"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Datum_valute" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Iznos">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;minInclusive value="0"/>
 *               &lt;totalDigits value="15"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Sifra_vlute">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="3"/>
 *               &lt;pattern value="[A-Z]{3}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Id_poruke" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="MT910" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Poruka", propOrder = {
    "banka",
    "idPorukeNaloga",
    "datumValute",
    "iznos",
    "sifraVlute"
})
public class Poruka {

    @XmlElement(name = "Banka", required = true)
    protected TBanka banka;
    @XmlElement(name = "Id_poruke_naloga", required = true)
    protected String idPorukeNaloga;
    @XmlElement(name = "Datum_valute", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(name = "Iznos", required = true)
    protected BigDecimal iznos;
    @XmlElement(name = "Sifra_vlute", required = true)
    protected String sifraVlute;
    @XmlAttribute(name = "Id_poruke", required = true)
    protected String idPoruke;

    /**
     * Gets the value of the banka property.
     * 
     * @return
     *     possible object is
     *     {@link TBanka }
     *     
     */
    public TBanka getBanka() {
        return banka;
    }

    /**
     * Sets the value of the banka property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBanka }
     *     
     */
    public void setBanka(TBanka value) {
        this.banka = value;
    }

    /**
     * Gets the value of the idPorukeNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPorukeNaloga() {
        return idPorukeNaloga;
    }

    /**
     * Sets the value of the idPorukeNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPorukeNaloga(String value) {
        this.idPorukeNaloga = value;
    }

    /**
     * Gets the value of the datumValute property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumValute() {
        return datumValute;
    }

    /**
     * Sets the value of the datumValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumValute(XMLGregorianCalendar value) {
        this.datumValute = value;
    }

    /**
     * Gets the value of the iznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIznos() {
        return iznos;
    }

    /**
     * Sets the value of the iznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIznos(BigDecimal value) {
        this.iznos = value;
    }

    /**
     * Gets the value of the sifraVlute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraVlute() {
        return sifraVlute;
    }

    /**
     * Sets the value of the sifraVlute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraVlute(String value) {
        this.sifraVlute = value;
    }

    /**
     * Gets the value of the idPoruke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPoruke() {
        if (idPoruke == null) {
            return "MT910";
        } else {
            return idPoruke;
        }
    }

    /**
     * Sets the value of the idPoruke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPoruke(String value) {
        this.idPoruke = value;
    }

}
