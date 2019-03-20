
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>driverFingerPojo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="driverFingerPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="section" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fingersign" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="recordtime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="personid" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="fingcat" type="{http://www.w3.org/2001/XMLSchema}base64Binary" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "driverFingerPojo")
public class DriverFingerPojo {

    @XmlAttribute(name = "section")
    protected String section;
    @XmlAttribute(name = "fingersign")
    protected Integer fingersign;
    @XmlAttribute(name = "recordtime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar recordtime;
    @XmlAttribute(name = "personid")
    protected Integer personid;
    @XmlAttribute(name = "fingcat")
    protected byte[] fingcat;

    /**
     * 获取section属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSection() {
        return section;
    }

    /**
     * 设置section属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSection(String value) {
        this.section = value;
    }

    /**
     * 获取fingersign属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFingersign() {
        return fingersign;
    }

    /**
     * 设置fingersign属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFingersign(Integer value) {
        this.fingersign = value;
    }

    /**
     * 获取recordtime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecordtime() {
        return recordtime;
    }

    /**
     * 设置recordtime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecordtime(XMLGregorianCalendar value) {
        this.recordtime = value;
    }

    /**
     * 获取personid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPersonid() {
        return personid;
    }

    /**
     * 设置personid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPersonid(Integer value) {
        this.personid = value;
    }

    /**
     * 获取fingcat属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFingcat() {
        return fingcat;
    }

    /**
     * 设置fingcat属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFingcat(byte[] value) {
        this.fingcat = value;
    }

}
