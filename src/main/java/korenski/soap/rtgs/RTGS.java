//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.08 at 03:44:03 PM CEST 
//


package korenski.soap.rtgs;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RTGS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTGS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Banka_poverilac" type="{http://korenski/soap/rtgs}TBanka"/>
 *         &lt;element name="Banka_duznik" type="{http://korenski/soap/rtgs}TBanka"/>
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Iznos">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="15"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Stavka_prenosa">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ID_poruke">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="1"/>
 *                         &lt;maxLength value="50"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Duznik-nalogodavac">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="1"/>
 *                         &lt;maxLength value="200"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Primalac-poverilac">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="1"/>
 *                         &lt;maxLength value="200"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Svrha_placanja">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="1"/>
 *                         &lt;maxLength value="200"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Podaci_o_placanju">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Finansijski_podaci_duznik" type="{http://korenski/soap/rtgs}TFinansijski_podaci"/>
 *                             &lt;element name="Finansijski_podaci_poverilac" type="{http://korenski/soap/rtgs}TFinansijski_podaci"/>
 *                             &lt;element name="Iznos">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;fractionDigits value="2"/>
 *                                   &lt;totalDigits value="15"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="Datum_valute" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                             &lt;element name="Oznaka_valute">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="3"/>
 *                                   &lt;pattern value="[A-Z]{3}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="Datum_naloga" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Sifra_prometa" type="{http://www.w3.org/2001/XMLSchema}string" fixed="MT103" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTGS", propOrder = {
    "bankaPoverilac",
    "bankaDuznik",
    "datum",
    "iznos",
    "stavkaPrenosa"
})
public class RTGS {

    @XmlElement(name = "Banka_poverilac", required = true)
    protected TBanka bankaPoverilac;
    @XmlElement(name = "Banka_duznik", required = true)
    protected TBanka bankaDuznik;
    @XmlElement(name = "Datum", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datum;
    @XmlElement(name = "Iznos", required = true)
    protected BigDecimal iznos;
    @XmlElement(name = "Stavka_prenosa", required = true)
    protected RTGS.StavkaPrenosa stavkaPrenosa;
    @XmlAttribute(name = "Sifra_prometa")
    protected String sifraPrometa;

    /**
     * Gets the value of the bankaPoverilac property.
     * 
     * @return
     *     possible object is
     *     {@link TBanka }
     *     
     */
    public TBanka getBankaPoverilac() {
        return bankaPoverilac;
    }

    /**
     * Sets the value of the bankaPoverilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBanka }
     *     
     */
    public void setBankaPoverilac(TBanka value) {
        this.bankaPoverilac = value;
    }

    /**
     * Gets the value of the bankaDuznik property.
     * 
     * @return
     *     possible object is
     *     {@link TBanka }
     *     
     */
    public TBanka getBankaDuznik() {
        return bankaDuznik;
    }

    /**
     * Sets the value of the bankaDuznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBanka }
     *     
     */
    public void setBankaDuznik(TBanka value) {
        this.bankaDuznik = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
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
     * Gets the value of the stavkaPrenosa property.
     * 
     * @return
     *     possible object is
     *     {@link RTGS.StavkaPrenosa }
     *     
     */
    public RTGS.StavkaPrenosa getStavkaPrenosa() {
        return stavkaPrenosa;
    }

    /**
     * Sets the value of the stavkaPrenosa property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTGS.StavkaPrenosa }
     *     
     */
    public void setStavkaPrenosa(RTGS.StavkaPrenosa value) {
        this.stavkaPrenosa = value;
    }

    /**
     * Gets the value of the sifraPrometa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraPrometa() {
        if (sifraPrometa == null) {
            return "MT103";
        } else {
            return sifraPrometa;
        }
    }

    /**
     * Sets the value of the sifraPrometa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraPrometa(String value) {
        this.sifraPrometa = value;
    }


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
     *         &lt;element name="ID_poruke">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="1"/>
     *               &lt;maxLength value="50"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Duznik-nalogodavac">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="1"/>
     *               &lt;maxLength value="200"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Primalac-poverilac">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="1"/>
     *               &lt;maxLength value="200"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Svrha_placanja">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="1"/>
     *               &lt;maxLength value="200"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Podaci_o_placanju">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Finansijski_podaci_duznik" type="{http://korenski/soap/rtgs}TFinansijski_podaci"/>
     *                   &lt;element name="Finansijski_podaci_poverilac" type="{http://korenski/soap/rtgs}TFinansijski_podaci"/>
     *                   &lt;element name="Iznos">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;fractionDigits value="2"/>
     *                         &lt;totalDigits value="15"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="Datum_valute" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                   &lt;element name="Oznaka_valute">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="3"/>
     *                         &lt;pattern value="[A-Z]{3}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="Datum_naloga" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "idPoruke",
        "duznikNalogodavac",
        "primalacPoverilac",
        "svrhaPlacanja",
        "podaciOPlacanju"
    })
    public static class StavkaPrenosa {

        @XmlElement(name = "ID_poruke", required = true)
        protected String idPoruke;
        @XmlElement(name = "Duznik-nalogodavac", required = true)
        protected String duznikNalogodavac;
        @XmlElement(name = "Primalac-poverilac", required = true)
        protected String primalacPoverilac;
        @XmlElement(name = "Svrha_placanja", required = true)
        protected String svrhaPlacanja;
        @XmlElement(name = "Podaci_o_placanju", required = true)
        protected RTGS.StavkaPrenosa.PodaciOPlacanju podaciOPlacanju;

        /**
         * Gets the value of the idPoruke property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIDPoruke() {
            return idPoruke;
        }

        /**
         * Sets the value of the idPoruke property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIDPoruke(String value) {
            this.idPoruke = value;
        }

        /**
         * Gets the value of the duznikNalogodavac property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDuznikNalogodavac() {
            return duznikNalogodavac;
        }

        /**
         * Sets the value of the duznikNalogodavac property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDuznikNalogodavac(String value) {
            this.duznikNalogodavac = value;
        }

        /**
         * Gets the value of the primalacPoverilac property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrimalacPoverilac() {
            return primalacPoverilac;
        }

        /**
         * Sets the value of the primalacPoverilac property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrimalacPoverilac(String value) {
            this.primalacPoverilac = value;
        }

        /**
         * Gets the value of the svrhaPlacanja property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSvrhaPlacanja() {
            return svrhaPlacanja;
        }

        /**
         * Sets the value of the svrhaPlacanja property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSvrhaPlacanja(String value) {
            this.svrhaPlacanja = value;
        }

        /**
         * Gets the value of the podaciOPlacanju property.
         * 
         * @return
         *     possible object is
         *     {@link RTGS.StavkaPrenosa.PodaciOPlacanju }
         *     
         */
        public RTGS.StavkaPrenosa.PodaciOPlacanju getPodaciOPlacanju() {
            return podaciOPlacanju;
        }

        /**
         * Sets the value of the podaciOPlacanju property.
         * 
         * @param value
         *     allowed object is
         *     {@link RTGS.StavkaPrenosa.PodaciOPlacanju }
         *     
         */
        public void setPodaciOPlacanju(RTGS.StavkaPrenosa.PodaciOPlacanju value) {
            this.podaciOPlacanju = value;
        }


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
         *         &lt;element name="Finansijski_podaci_duznik" type="{http://korenski/soap/rtgs}TFinansijski_podaci"/>
         *         &lt;element name="Finansijski_podaci_poverilac" type="{http://korenski/soap/rtgs}TFinansijski_podaci"/>
         *         &lt;element name="Iznos">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;fractionDigits value="2"/>
         *               &lt;totalDigits value="15"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="Datum_valute" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
         *         &lt;element name="Oznaka_valute">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="3"/>
         *               &lt;pattern value="[A-Z]{3}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="Datum_naloga" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
            "finansijskiPodaciDuznik",
            "finansijskiPodaciPoverilac",
            "iznos",
            "datumValute",
            "oznakaValute",
            "datumNaloga"
        })
        public static class PodaciOPlacanju {

            @XmlElement(name = "Finansijski_podaci_duznik", required = true)
            protected TFinansijskiPodaci finansijskiPodaciDuznik;
            @XmlElement(name = "Finansijski_podaci_poverilac", required = true)
            protected TFinansijskiPodaci finansijskiPodaciPoverilac;
            @XmlElement(name = "Iznos", required = true)
            protected BigDecimal iznos;
            @XmlElement(name = "Datum_valute", required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar datumValute;
            @XmlElement(name = "Oznaka_valute", required = true)
            protected String oznakaValute;
            @XmlElement(name = "Datum_naloga", required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar datumNaloga;

            /**
             * Gets the value of the finansijskiPodaciDuznik property.
             * 
             * @return
             *     possible object is
             *     {@link TFinansijskiPodaci }
             *     
             */
            public TFinansijskiPodaci getFinansijskiPodaciDuznik() {
                return finansijskiPodaciDuznik;
            }

            /**
             * Sets the value of the finansijskiPodaciDuznik property.
             * 
             * @param value
             *     allowed object is
             *     {@link TFinansijskiPodaci }
             *     
             */
            public void setFinansijskiPodaciDuznik(TFinansijskiPodaci value) {
                this.finansijskiPodaciDuznik = value;
            }

            /**
             * Gets the value of the finansijskiPodaciPoverilac property.
             * 
             * @return
             *     possible object is
             *     {@link TFinansijskiPodaci }
             *     
             */
            public TFinansijskiPodaci getFinansijskiPodaciPoverilac() {
                return finansijskiPodaciPoverilac;
            }

            /**
             * Sets the value of the finansijskiPodaciPoverilac property.
             * 
             * @param value
             *     allowed object is
             *     {@link TFinansijskiPodaci }
             *     
             */
            public void setFinansijskiPodaciPoverilac(TFinansijskiPodaci value) {
                this.finansijskiPodaciPoverilac = value;
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
             * Gets the value of the oznakaValute property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOznakaValute() {
                return oznakaValute;
            }

            /**
             * Sets the value of the oznakaValute property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOznakaValute(String value) {
                this.oznakaValute = value;
            }

            /**
             * Gets the value of the datumNaloga property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDatumNaloga() {
                return datumNaloga;
            }

            /**
             * Sets the value of the datumNaloga property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDatumNaloga(XMLGregorianCalendar value) {
                this.datumNaloga = value;
            }

        }

    }

}
