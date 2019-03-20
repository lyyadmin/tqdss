
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>driverAlcoholPojo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="driverAlcoholPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="alcoholid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driverid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="jobid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="checktime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="alcoholvalue" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="alcoholstate" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="clientid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driverName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "driverAlcoholPojo")
public class DriverAlcoholPojo {

    @XmlAttribute(name = "alcoholid", required = true)
    protected int alcoholid;
    @XmlAttribute(name = "driverid", required = true)
    protected int driverid;
    @XmlAttribute(name = "jobid", required = true)
    protected int jobid;
    @XmlAttribute(name = "checktime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar checktime;
    @XmlAttribute(name = "alcoholvalue", required = true)
    protected int alcoholvalue;
    @XmlAttribute(name = "alcoholstate", required = true)
    protected int alcoholstate;
    @XmlAttribute(name = "clientid")
    protected String clientid;
    @XmlAttribute(name = "driverName")
    protected String driverName;

    /**
     * 获取alcoholid属性的值。
     * 
     */
    public int getAlcoholid() {
        return alcoholid;
    }

    /**
     * 设置alcoholid属性的值。
     * 
     */
    public void setAlcoholid(int value) {
        this.alcoholid = value;
    }

    /**
     * 获取driverid属性的值。
     * 
     */
    public int getDriverid() {
        return driverid;
    }

    /**
     * 设置driverid属性的值。
     * 
     */
    public void setDriverid(int value) {
        this.driverid = value;
    }

    /**
     * 获取jobid属性的值。
     * 
     */
    public int getJobid() {
        return jobid;
    }

    /**
     * 设置jobid属性的值。
     * 
     */
    public void setJobid(int value) {
        this.jobid = value;
    }

    /**
     * 获取checktime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getChecktime() {
        return checktime;
    }

    /**
     * 设置checktime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setChecktime(XMLGregorianCalendar value) {
        this.checktime = value;
    }

    /**
     * 获取alcoholvalue属性的值。
     * 
     */
    public int getAlcoholvalue() {
        return alcoholvalue;
    }

    /**
     * 设置alcoholvalue属性的值。
     * 
     */
    public void setAlcoholvalue(int value) {
        this.alcoholvalue = value;
    }

    /**
     * 获取alcoholstate属性的值。
     * 
     */
    public int getAlcoholstate() {
        return alcoholstate;
    }

    /**
     * 设置alcoholstate属性的值。
     * 
     */
    public void setAlcoholstate(int value) {
        this.alcoholstate = value;
    }

    /**
     * 获取clientid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientid() {
        return clientid;
    }

    /**
     * 设置clientid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientid(String value) {
        this.clientid = value;
    }

    /**
     * 获取driverName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * 设置driverName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverName(String value) {
        this.driverName = value;
    }

}
