
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>locoPlanPojo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="locoPlanPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="jobid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="depstation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="plantrainnum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="plandeptime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="loco" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="calltime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="driver1" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver2" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver3" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driverid1" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driverid2" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driverid3" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="sdat1" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="sdat2" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="sdat3" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="edat1" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="edat2" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="edat3" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="realtrainnum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="realdeptime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="lowercode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dispmancode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="displococode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="outportcode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ptaskdicid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="planouttime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="planondutytime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="planmodel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="planloco" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="jobnode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="railPath" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="taskName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="arvTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "locoPlanPojo")
public class LocoPlanPojo {

    @XmlAttribute(name = "jobid", required = true)
    protected int jobid;
    @XmlAttribute(name = "depstation")
    protected String depstation;
    @XmlAttribute(name = "plantrainnum")
    protected String plantrainnum;
    @XmlAttribute(name = "plandeptime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar plandeptime;
    @XmlAttribute(name = "model")
    protected String model;
    @XmlAttribute(name = "loco", required = true)
    protected int loco;
    @XmlAttribute(name = "calltime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calltime;
    @XmlAttribute(name = "driver1")
    protected String driver1;
    @XmlAttribute(name = "driver2")
    protected String driver2;
    @XmlAttribute(name = "driver3")
    protected String driver3;
    @XmlAttribute(name = "driverid1", required = true)
    protected int driverid1;
    @XmlAttribute(name = "driverid2", required = true)
    protected int driverid2;
    @XmlAttribute(name = "driverid3", required = true)
    protected int driverid3;
    @XmlAttribute(name = "sdat1")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sdat1;
    @XmlAttribute(name = "sdat2")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sdat2;
    @XmlAttribute(name = "sdat3")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sdat3;
    @XmlAttribute(name = "edat1")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar edat1;
    @XmlAttribute(name = "edat2")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar edat2;
    @XmlAttribute(name = "edat3")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar edat3;
    @XmlAttribute(name = "realtrainnum")
    protected String realtrainnum;
    @XmlAttribute(name = "realdeptime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar realdeptime;
    @XmlAttribute(name = "lowercode")
    protected String lowercode;
    @XmlAttribute(name = "dispmancode")
    protected String dispmancode;
    @XmlAttribute(name = "displococode")
    protected String displococode;
    @XmlAttribute(name = "outportcode")
    protected String outportcode;
    @XmlAttribute(name = "ptaskdicid", required = true)
    protected int ptaskdicid;
    @XmlAttribute(name = "planouttime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar planouttime;
    @XmlAttribute(name = "planondutytime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar planondutytime;
    @XmlAttribute(name = "planmodel")
    protected String planmodel;
    @XmlAttribute(name = "planloco", required = true)
    protected int planloco;
    @XmlAttribute(name = "jobnode")
    protected String jobnode;
    @XmlAttribute(name = "railPath")
    protected String railPath;
    @XmlAttribute(name = "taskName")
    protected String taskName;
    @XmlAttribute(name = "arvTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar arvTime;

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
     * 获取depstation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepstation() {
        return depstation;
    }

    /**
     * 设置depstation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepstation(String value) {
        this.depstation = value;
    }

    /**
     * 获取plantrainnum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlantrainnum() {
        return plantrainnum;
    }

    /**
     * 设置plantrainnum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlantrainnum(String value) {
        this.plantrainnum = value;
    }

    /**
     * 获取plandeptime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlandeptime() {
        return plandeptime;
    }

    /**
     * 设置plandeptime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlandeptime(XMLGregorianCalendar value) {
        this.plandeptime = value;
    }

    /**
     * 获取model属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置model属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * 获取loco属性的值。
     * 
     */
    public int getLoco() {
        return loco;
    }

    /**
     * 设置loco属性的值。
     * 
     */
    public void setLoco(int value) {
        this.loco = value;
    }

    /**
     * 获取calltime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalltime() {
        return calltime;
    }

    /**
     * 设置calltime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalltime(XMLGregorianCalendar value) {
        this.calltime = value;
    }

    /**
     * 获取driver1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver1() {
        return driver1;
    }

    /**
     * 设置driver1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver1(String value) {
        this.driver1 = value;
    }

    /**
     * 获取driver2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver2() {
        return driver2;
    }

    /**
     * 设置driver2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver2(String value) {
        this.driver2 = value;
    }

    /**
     * 获取driver3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver3() {
        return driver3;
    }

    /**
     * 设置driver3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver3(String value) {
        this.driver3 = value;
    }

    /**
     * 获取driverid1属性的值。
     * 
     */
    public int getDriverid1() {
        return driverid1;
    }

    /**
     * 设置driverid1属性的值。
     * 
     */
    public void setDriverid1(int value) {
        this.driverid1 = value;
    }

    /**
     * 获取driverid2属性的值。
     * 
     */
    public int getDriverid2() {
        return driverid2;
    }

    /**
     * 设置driverid2属性的值。
     * 
     */
    public void setDriverid2(int value) {
        this.driverid2 = value;
    }

    /**
     * 获取driverid3属性的值。
     * 
     */
    public int getDriverid3() {
        return driverid3;
    }

    /**
     * 设置driverid3属性的值。
     * 
     */
    public void setDriverid3(int value) {
        this.driverid3 = value;
    }

    /**
     * 获取sdat1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSdat1() {
        return sdat1;
    }

    /**
     * 设置sdat1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSdat1(XMLGregorianCalendar value) {
        this.sdat1 = value;
    }

    /**
     * 获取sdat2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSdat2() {
        return sdat2;
    }

    /**
     * 设置sdat2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSdat2(XMLGregorianCalendar value) {
        this.sdat2 = value;
    }

    /**
     * 获取sdat3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSdat3() {
        return sdat3;
    }

    /**
     * 设置sdat3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSdat3(XMLGregorianCalendar value) {
        this.sdat3 = value;
    }

    /**
     * 获取edat1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEdat1() {
        return edat1;
    }

    /**
     * 设置edat1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEdat1(XMLGregorianCalendar value) {
        this.edat1 = value;
    }

    /**
     * 获取edat2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEdat2() {
        return edat2;
    }

    /**
     * 设置edat2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEdat2(XMLGregorianCalendar value) {
        this.edat2 = value;
    }

    /**
     * 获取edat3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEdat3() {
        return edat3;
    }

    /**
     * 设置edat3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEdat3(XMLGregorianCalendar value) {
        this.edat3 = value;
    }

    /**
     * 获取realtrainnum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealtrainnum() {
        return realtrainnum;
    }

    /**
     * 设置realtrainnum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealtrainnum(String value) {
        this.realtrainnum = value;
    }

    /**
     * 获取realdeptime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRealdeptime() {
        return realdeptime;
    }

    /**
     * 设置realdeptime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRealdeptime(XMLGregorianCalendar value) {
        this.realdeptime = value;
    }

    /**
     * 获取lowercode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLowercode() {
        return lowercode;
    }

    /**
     * 设置lowercode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLowercode(String value) {
        this.lowercode = value;
    }

    /**
     * 获取dispmancode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispmancode() {
        return dispmancode;
    }

    /**
     * 设置dispmancode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispmancode(String value) {
        this.dispmancode = value;
    }

    /**
     * 获取displococode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplococode() {
        return displococode;
    }

    /**
     * 设置displococode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplococode(String value) {
        this.displococode = value;
    }

    /**
     * 获取outportcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutportcode() {
        return outportcode;
    }

    /**
     * 设置outportcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutportcode(String value) {
        this.outportcode = value;
    }

    /**
     * 获取ptaskdicid属性的值。
     * 
     */
    public int getPtaskdicid() {
        return ptaskdicid;
    }

    /**
     * 设置ptaskdicid属性的值。
     * 
     */
    public void setPtaskdicid(int value) {
        this.ptaskdicid = value;
    }

    /**
     * 获取planouttime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlanouttime() {
        return planouttime;
    }

    /**
     * 设置planouttime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlanouttime(XMLGregorianCalendar value) {
        this.planouttime = value;
    }

    /**
     * 获取planondutytime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlanondutytime() {
        return planondutytime;
    }

    /**
     * 设置planondutytime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlanondutytime(XMLGregorianCalendar value) {
        this.planondutytime = value;
    }

    /**
     * 获取planmodel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanmodel() {
        return planmodel;
    }

    /**
     * 设置planmodel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanmodel(String value) {
        this.planmodel = value;
    }

    /**
     * 获取planloco属性的值。
     * 
     */
    public int getPlanloco() {
        return planloco;
    }

    /**
     * 设置planloco属性的值。
     * 
     */
    public void setPlanloco(int value) {
        this.planloco = value;
    }

    /**
     * 获取jobnode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobnode() {
        return jobnode;
    }

    /**
     * 设置jobnode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobnode(String value) {
        this.jobnode = value;
    }

    /**
     * 获取railPath属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRailPath() {
        return railPath;
    }

    /**
     * 设置railPath属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRailPath(String value) {
        this.railPath = value;
    }

    /**
     * 获取taskName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置taskName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskName(String value) {
        this.taskName = value;
    }

    /**
     * 获取arvTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArvTime() {
        return arvTime;
    }

    /**
     * 设置arvTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArvTime(XMLGregorianCalendar value) {
        this.arvTime = value;
    }

}
