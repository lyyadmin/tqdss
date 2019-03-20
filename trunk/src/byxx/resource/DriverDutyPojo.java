
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>driverDutyPojo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="driverDutyPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driverid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ondutytime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="ondutytype" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="score" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ondutycode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="examtime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="jobid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="offdutycode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="offdutytime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="offdutytype" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="workNo" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driverName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="trainNum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lowerCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dispLocoCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dispManCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="loco" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="taskId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="taskName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="note" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cargo" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="team" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="group" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driverSecCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driverSecStr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "driverDutyPojo")
public class DriverDutyPojo {

    @XmlAttribute(name = "id", required = true)
    protected int id;
    @XmlAttribute(name = "driverid", required = true)
    protected int driverid;
    @XmlAttribute(name = "ondutytime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ondutytime;
    @XmlAttribute(name = "ondutytype", required = true)
    protected int ondutytype;
    @XmlAttribute(name = "score", required = true)
    protected int score;
    @XmlAttribute(name = "ondutycode")
    protected String ondutycode;
    @XmlAttribute(name = "examtime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar examtime;
    @XmlAttribute(name = "jobid", required = true)
    protected int jobid;
    @XmlAttribute(name = "offdutycode")
    protected String offdutycode;
    @XmlAttribute(name = "offdutytime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar offdutytime;
    @XmlAttribute(name = "offdutytype", required = true)
    protected int offdutytype;
    @XmlAttribute(name = "workNo", required = true)
    protected int workNo;
    @XmlAttribute(name = "driverName")
    protected String driverName;
    @XmlAttribute(name = "trainNum")
    protected String trainNum;
    @XmlAttribute(name = "lowerCode")
    protected String lowerCode;
    @XmlAttribute(name = "dispLocoCode")
    protected String dispLocoCode;
    @XmlAttribute(name = "dispManCode")
    protected String dispManCode;
    @XmlAttribute(name = "model")
    protected String model;
    @XmlAttribute(name = "loco", required = true)
    protected int loco;
    @XmlAttribute(name = "taskId", required = true)
    protected int taskId;
    @XmlAttribute(name = "taskName")
    protected String taskName;
    @XmlAttribute(name = "note")
    protected String note;
    @XmlAttribute(name = "cargo", required = true)
    protected int cargo;
    @XmlAttribute(name = "team")
    protected String team;
    @XmlAttribute(name = "group")
    protected String group;
    @XmlAttribute(name = "driverSecCode")
    protected String driverSecCode;
    @XmlAttribute(name = "driverSecStr")
    protected String driverSecStr;

    /**
     * 获取id属性的值。
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     */
    public void setId(int value) {
        this.id = value;
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
     * 获取ondutytime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOndutytime() {
        return ondutytime;
    }

    /**
     * 设置ondutytime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOndutytime(XMLGregorianCalendar value) {
        this.ondutytime = value;
    }

    /**
     * 获取ondutytype属性的值。
     * 
     */
    public int getOndutytype() {
        return ondutytype;
    }

    /**
     * 设置ondutytype属性的值。
     * 
     */
    public void setOndutytype(int value) {
        this.ondutytype = value;
    }

    /**
     * 获取score属性的值。
     * 
     */
    public int getScore() {
        return score;
    }

    /**
     * 设置score属性的值。
     * 
     */
    public void setScore(int value) {
        this.score = value;
    }

    /**
     * 获取ondutycode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOndutycode() {
        return ondutycode;
    }

    /**
     * 设置ondutycode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOndutycode(String value) {
        this.ondutycode = value;
    }

    /**
     * 获取examtime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExamtime() {
        return examtime;
    }

    /**
     * 设置examtime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExamtime(XMLGregorianCalendar value) {
        this.examtime = value;
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
     * 获取offdutycode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffdutycode() {
        return offdutycode;
    }

    /**
     * 设置offdutycode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffdutycode(String value) {
        this.offdutycode = value;
    }

    /**
     * 获取offdutytime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOffdutytime() {
        return offdutytime;
    }

    /**
     * 设置offdutytime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOffdutytime(XMLGregorianCalendar value) {
        this.offdutytime = value;
    }

    /**
     * 获取offdutytype属性的值。
     * 
     */
    public int getOffdutytype() {
        return offdutytype;
    }

    /**
     * 设置offdutytype属性的值。
     * 
     */
    public void setOffdutytype(int value) {
        this.offdutytype = value;
    }

    /**
     * 获取workNo属性的值。
     * 
     */
    public int getWorkNo() {
        return workNo;
    }

    /**
     * 设置workNo属性的值。
     * 
     */
    public void setWorkNo(int value) {
        this.workNo = value;
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

    /**
     * 获取trainNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainNum() {
        return trainNum;
    }

    /**
     * 设置trainNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainNum(String value) {
        this.trainNum = value;
    }

    /**
     * 获取lowerCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLowerCode() {
        return lowerCode;
    }

    /**
     * 设置lowerCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLowerCode(String value) {
        this.lowerCode = value;
    }

    /**
     * 获取dispLocoCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispLocoCode() {
        return dispLocoCode;
    }

    /**
     * 设置dispLocoCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispLocoCode(String value) {
        this.dispLocoCode = value;
    }

    /**
     * 获取dispManCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispManCode() {
        return dispManCode;
    }

    /**
     * 设置dispManCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispManCode(String value) {
        this.dispManCode = value;
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
     * 获取taskId属性的值。
     * 
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * 设置taskId属性的值。
     * 
     */
    public void setTaskId(int value) {
        this.taskId = value;
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
     * 获取note属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置note属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * 获取cargo属性的值。
     * 
     */
    public int getCargo() {
        return cargo;
    }

    /**
     * 设置cargo属性的值。
     * 
     */
    public void setCargo(int value) {
        this.cargo = value;
    }

    /**
     * 获取team属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeam() {
        return team;
    }

    /**
     * 设置team属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeam(String value) {
        this.team = value;
    }

    /**
     * 获取group属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroup() {
        return group;
    }

    /**
     * 设置group属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroup(String value) {
        this.group = value;
    }

    /**
     * 获取driverSecCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverSecCode() {
        return driverSecCode;
    }

    /**
     * 设置driverSecCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverSecCode(String value) {
        this.driverSecCode = value;
    }

    /**
     * 获取driverSecStr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverSecStr() {
        return driverSecStr;
    }

    /**
     * 设置driverSecStr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverSecStr(String value) {
        this.driverSecStr = value;
    }

}
