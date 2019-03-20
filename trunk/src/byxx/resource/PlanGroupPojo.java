
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>planGroupPojo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="planGroupPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="plangroupid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="taskdicid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="dutytype" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="place" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="section" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver1" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driver2" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driver3" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driver4" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driverstate1" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driverstate2" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driverstate3" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driverstate4" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="idx" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "planGroupPojo")
public class PlanGroupPojo {

    @XmlAttribute(name = "plangroupid", required = true)
    protected int plangroupid;
    @XmlAttribute(name = "taskdicid", required = true)
    protected int taskdicid;
    @XmlAttribute(name = "dutytype", required = true)
    protected int dutytype;
    @XmlAttribute(name = "place")
    protected String place;
    @XmlAttribute(name = "section")
    protected String section;
    @XmlAttribute(name = "driver1", required = true)
    protected int driver1;
    @XmlAttribute(name = "driver2", required = true)
    protected int driver2;
    @XmlAttribute(name = "driver3", required = true)
    protected int driver3;
    @XmlAttribute(name = "driver4", required = true)
    protected int driver4;
    @XmlAttribute(name = "driverstate1", required = true)
    protected int driverstate1;
    @XmlAttribute(name = "driverstate2", required = true)
    protected int driverstate2;
    @XmlAttribute(name = "driverstate3", required = true)
    protected int driverstate3;
    @XmlAttribute(name = "driverstate4", required = true)
    protected int driverstate4;
    @XmlAttribute(name = "idx", required = true)
    protected int idx;

    /**
     * 获取plangroupid属性的值。
     * 
     */
    public int getPlangroupid() {
        return plangroupid;
    }

    /**
     * 设置plangroupid属性的值。
     * 
     */
    public void setPlangroupid(int value) {
        this.plangroupid = value;
    }

    /**
     * 获取taskdicid属性的值。
     * 
     */
    public int getTaskdicid() {
        return taskdicid;
    }

    /**
     * 设置taskdicid属性的值。
     * 
     */
    public void setTaskdicid(int value) {
        this.taskdicid = value;
    }

    /**
     * 获取dutytype属性的值。
     * 
     */
    public int getDutytype() {
        return dutytype;
    }

    /**
     * 设置dutytype属性的值。
     * 
     */
    public void setDutytype(int value) {
        this.dutytype = value;
    }

    /**
     * 获取place属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlace() {
        return place;
    }

    /**
     * 设置place属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlace(String value) {
        this.place = value;
    }

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
     * 获取driver1属性的值。
     * 
     */
    public int getDriver1() {
        return driver1;
    }

    /**
     * 设置driver1属性的值。
     * 
     */
    public void setDriver1(int value) {
        this.driver1 = value;
    }

    /**
     * 获取driver2属性的值。
     * 
     */
    public int getDriver2() {
        return driver2;
    }

    /**
     * 设置driver2属性的值。
     * 
     */
    public void setDriver2(int value) {
        this.driver2 = value;
    }

    /**
     * 获取driver3属性的值。
     * 
     */
    public int getDriver3() {
        return driver3;
    }

    /**
     * 设置driver3属性的值。
     * 
     */
    public void setDriver3(int value) {
        this.driver3 = value;
    }

    /**
     * 获取driver4属性的值。
     * 
     */
    public int getDriver4() {
        return driver4;
    }

    /**
     * 设置driver4属性的值。
     * 
     */
    public void setDriver4(int value) {
        this.driver4 = value;
    }

    /**
     * 获取driverstate1属性的值。
     * 
     */
    public int getDriverstate1() {
        return driverstate1;
    }

    /**
     * 设置driverstate1属性的值。
     * 
     */
    public void setDriverstate1(int value) {
        this.driverstate1 = value;
    }

    /**
     * 获取driverstate2属性的值。
     * 
     */
    public int getDriverstate2() {
        return driverstate2;
    }

    /**
     * 设置driverstate2属性的值。
     * 
     */
    public void setDriverstate2(int value) {
        this.driverstate2 = value;
    }

    /**
     * 获取driverstate3属性的值。
     * 
     */
    public int getDriverstate3() {
        return driverstate3;
    }

    /**
     * 设置driverstate3属性的值。
     * 
     */
    public void setDriverstate3(int value) {
        this.driverstate3 = value;
    }

    /**
     * 获取driverstate4属性的值。
     * 
     */
    public int getDriverstate4() {
        return driverstate4;
    }

    /**
     * 设置driverstate4属性的值。
     * 
     */
    public void setDriverstate4(int value) {
        this.driverstate4 = value;
    }

    /**
     * 获取idx属性的值。
     * 
     */
    public int getIdx() {
        return idx;
    }

    /**
     * 设置idx属性的值。
     * 
     */
    public void setIdx(int value) {
        this.idx = value;
    }

}
