
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>driverPlanPojo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="driverPlanPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="jobid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="plandepstation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="plantrainnum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="plandeptime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="loco" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="dispmancode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lowercode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="realdeptime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="planlivehousetime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="planondutytime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="plancalltime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="note" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ondutycode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="offdutycode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="taskdicid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="realcalltime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="delaycode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="housecode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="realdeptrainnum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver1" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driver2" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driver3" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="driver4" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ondutydate1" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="ondutydate2" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="ondutydate3" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="ondutydate4" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="offdutydate1" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="offdutydate2" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="offdutydate3" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="offdutydate4" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="jobnode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="arvTrain" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="arvStation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="arvTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="driver1OnDutyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver2OnDutyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver3OnDutyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver4OnDutyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver1OffDutyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver2OffDutyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver3OffDutyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver4OffDutyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver1Team" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver2Team" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver3Team" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver4Team" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver1Group" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver2Group" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver3Group" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="driver4Group" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="taskName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "driverPlanPojo")
public class DriverPlanPojo {

    @XmlAttribute(name = "jobid", required = true)
    protected int jobid;
    @XmlAttribute(name = "plandepstation")
    protected String plandepstation;
    @XmlAttribute(name = "plantrainnum")
    protected String plantrainnum;
    @XmlAttribute(name = "plandeptime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar plandeptime;
    @XmlAttribute(name = "model")
    protected String model;
    @XmlAttribute(name = "loco", required = true)
    protected int loco;
    @XmlAttribute(name = "dispmancode")
    protected String dispmancode;
    @XmlAttribute(name = "lowercode")
    protected String lowercode;
    @XmlAttribute(name = "realdeptime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar realdeptime;
    @XmlAttribute(name = "planlivehousetime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar planlivehousetime;
    @XmlAttribute(name = "planondutytime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar planondutytime;
    @XmlAttribute(name = "plancalltime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar plancalltime;
    @XmlAttribute(name = "note")
    protected String note;
    @XmlAttribute(name = "ondutycode")
    protected String ondutycode;
    @XmlAttribute(name = "offdutycode")
    protected String offdutycode;
    @XmlAttribute(name = "taskdicid", required = true)
    protected int taskdicid;
    @XmlAttribute(name = "realcalltime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar realcalltime;
    @XmlAttribute(name = "delaycode")
    protected String delaycode;
    @XmlAttribute(name = "housecode")
    protected String housecode;
    @XmlAttribute(name = "realdeptrainnum")
    protected String realdeptrainnum;
    @XmlAttribute(name = "driver1", required = true)
    protected int driver1;
    @XmlAttribute(name = "driver2", required = true)
    protected int driver2;
    @XmlAttribute(name = "driver3", required = true)
    protected int driver3;
    @XmlAttribute(name = "driver4", required = true)
    protected int driver4;
    @XmlAttribute(name = "ondutydate1")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ondutydate1;
    @XmlAttribute(name = "ondutydate2")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ondutydate2;
    @XmlAttribute(name = "ondutydate3")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ondutydate3;
    @XmlAttribute(name = "ondutydate4")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ondutydate4;
    @XmlAttribute(name = "offdutydate1")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar offdutydate1;
    @XmlAttribute(name = "offdutydate2")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar offdutydate2;
    @XmlAttribute(name = "offdutydate3")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar offdutydate3;
    @XmlAttribute(name = "offdutydate4")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar offdutydate4;
    @XmlAttribute(name = "jobnode")
    protected String jobnode;
    @XmlAttribute(name = "arvTrain")
    protected String arvTrain;
    @XmlAttribute(name = "arvStation")
    protected String arvStation;
    @XmlAttribute(name = "arvTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar arvTime;
    @XmlAttribute(name = "driver1OnDutyCode")
    protected String driver1OnDutyCode;
    @XmlAttribute(name = "driver2OnDutyCode")
    protected String driver2OnDutyCode;
    @XmlAttribute(name = "driver3OnDutyCode")
    protected String driver3OnDutyCode;
    @XmlAttribute(name = "driver4OnDutyCode")
    protected String driver4OnDutyCode;
    @XmlAttribute(name = "driver1OffDutyCode")
    protected String driver1OffDutyCode;
    @XmlAttribute(name = "driver2OffDutyCode")
    protected String driver2OffDutyCode;
    @XmlAttribute(name = "driver3OffDutyCode")
    protected String driver3OffDutyCode;
    @XmlAttribute(name = "driver4OffDutyCode")
    protected String driver4OffDutyCode;
    @XmlAttribute(name = "driver1Team")
    protected String driver1Team;
    @XmlAttribute(name = "driver2Team")
    protected String driver2Team;
    @XmlAttribute(name = "driver3Team")
    protected String driver3Team;
    @XmlAttribute(name = "driver4Team")
    protected String driver4Team;
    @XmlAttribute(name = "driver1Group")
    protected String driver1Group;
    @XmlAttribute(name = "driver2Group")
    protected String driver2Group;
    @XmlAttribute(name = "driver3Group")
    protected String driver3Group;
    @XmlAttribute(name = "driver4Group")
    protected String driver4Group;
    @XmlAttribute(name = "taskName")
    protected String taskName;

    /**
     * ��ȡjobid���Ե�ֵ��
     * 
     */
    public int getJobid() {
        return jobid;
    }

    /**
     * ����jobid���Ե�ֵ��
     * 
     */
    public void setJobid(int value) {
        this.jobid = value;
    }

    /**
     * ��ȡplandepstation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlandepstation() {
        return plandepstation;
    }

    /**
     * ����plandepstation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlandepstation(String value) {
        this.plandepstation = value;
    }

    /**
     * ��ȡplantrainnum���Ե�ֵ��
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
     * ����plantrainnum���Ե�ֵ��
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
     * ��ȡplandeptime���Ե�ֵ��
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
     * ����plandeptime���Ե�ֵ��
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
     * ��ȡmodel���Ե�ֵ��
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
     * ����model���Ե�ֵ��
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
     * ��ȡloco���Ե�ֵ��
     * 
     */
    public int getLoco() {
        return loco;
    }

    /**
     * ����loco���Ե�ֵ��
     * 
     */
    public void setLoco(int value) {
        this.loco = value;
    }

    /**
     * ��ȡdispmancode���Ե�ֵ��
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
     * ����dispmancode���Ե�ֵ��
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
     * ��ȡlowercode���Ե�ֵ��
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
     * ����lowercode���Ե�ֵ��
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
     * ��ȡrealdeptime���Ե�ֵ��
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
     * ����realdeptime���Ե�ֵ��
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
     * ��ȡplanlivehousetime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlanlivehousetime() {
        return planlivehousetime;
    }

    /**
     * ����planlivehousetime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlanlivehousetime(XMLGregorianCalendar value) {
        this.planlivehousetime = value;
    }

    /**
     * ��ȡplanondutytime���Ե�ֵ��
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
     * ����planondutytime���Ե�ֵ��
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
     * ��ȡplancalltime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlancalltime() {
        return plancalltime;
    }

    /**
     * ����plancalltime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlancalltime(XMLGregorianCalendar value) {
        this.plancalltime = value;
    }

    /**
     * ��ȡnote���Ե�ֵ��
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
     * ����note���Ե�ֵ��
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
     * ��ȡondutycode���Ե�ֵ��
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
     * ����ondutycode���Ե�ֵ��
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
     * ��ȡoffdutycode���Ե�ֵ��
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
     * ����offdutycode���Ե�ֵ��
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
     * ��ȡtaskdicid���Ե�ֵ��
     * 
     */
    public int getTaskdicid() {
        return taskdicid;
    }

    /**
     * ����taskdicid���Ե�ֵ��
     * 
     */
    public void setTaskdicid(int value) {
        this.taskdicid = value;
    }

    /**
     * ��ȡrealcalltime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRealcalltime() {
        return realcalltime;
    }

    /**
     * ����realcalltime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRealcalltime(XMLGregorianCalendar value) {
        this.realcalltime = value;
    }

    /**
     * ��ȡdelaycode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelaycode() {
        return delaycode;
    }

    /**
     * ����delaycode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelaycode(String value) {
        this.delaycode = value;
    }

    /**
     * ��ȡhousecode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHousecode() {
        return housecode;
    }

    /**
     * ����housecode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHousecode(String value) {
        this.housecode = value;
    }

    /**
     * ��ȡrealdeptrainnum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealdeptrainnum() {
        return realdeptrainnum;
    }

    /**
     * ����realdeptrainnum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealdeptrainnum(String value) {
        this.realdeptrainnum = value;
    }

    /**
     * ��ȡdriver1���Ե�ֵ��
     * 
     */
    public int getDriver1() {
        return driver1;
    }

    /**
     * ����driver1���Ե�ֵ��
     * 
     */
    public void setDriver1(int value) {
        this.driver1 = value;
    }

    /**
     * ��ȡdriver2���Ե�ֵ��
     * 
     */
    public int getDriver2() {
        return driver2;
    }

    /**
     * ����driver2���Ե�ֵ��
     * 
     */
    public void setDriver2(int value) {
        this.driver2 = value;
    }

    /**
     * ��ȡdriver3���Ե�ֵ��
     * 
     */
    public int getDriver3() {
        return driver3;
    }

    /**
     * ����driver3���Ե�ֵ��
     * 
     */
    public void setDriver3(int value) {
        this.driver3 = value;
    }

    /**
     * ��ȡdriver4���Ե�ֵ��
     * 
     */
    public int getDriver4() {
        return driver4;
    }

    /**
     * ����driver4���Ե�ֵ��
     * 
     */
    public void setDriver4(int value) {
        this.driver4 = value;
    }

    /**
     * ��ȡondutydate1���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOndutydate1() {
        return ondutydate1;
    }

    /**
     * ����ondutydate1���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOndutydate1(XMLGregorianCalendar value) {
        this.ondutydate1 = value;
    }

    /**
     * ��ȡondutydate2���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOndutydate2() {
        return ondutydate2;
    }

    /**
     * ����ondutydate2���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOndutydate2(XMLGregorianCalendar value) {
        this.ondutydate2 = value;
    }

    /**
     * ��ȡondutydate3���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOndutydate3() {
        return ondutydate3;
    }

    /**
     * ����ondutydate3���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOndutydate3(XMLGregorianCalendar value) {
        this.ondutydate3 = value;
    }

    /**
     * ��ȡondutydate4���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOndutydate4() {
        return ondutydate4;
    }

    /**
     * ����ondutydate4���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOndutydate4(XMLGregorianCalendar value) {
        this.ondutydate4 = value;
    }

    /**
     * ��ȡoffdutydate1���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOffdutydate1() {
        return offdutydate1;
    }

    /**
     * ����offdutydate1���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOffdutydate1(XMLGregorianCalendar value) {
        this.offdutydate1 = value;
    }

    /**
     * ��ȡoffdutydate2���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOffdutydate2() {
        return offdutydate2;
    }

    /**
     * ����offdutydate2���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOffdutydate2(XMLGregorianCalendar value) {
        this.offdutydate2 = value;
    }

    /**
     * ��ȡoffdutydate3���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOffdutydate3() {
        return offdutydate3;
    }

    /**
     * ����offdutydate3���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOffdutydate3(XMLGregorianCalendar value) {
        this.offdutydate3 = value;
    }

    /**
     * ��ȡoffdutydate4���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOffdutydate4() {
        return offdutydate4;
    }

    /**
     * ����offdutydate4���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOffdutydate4(XMLGregorianCalendar value) {
        this.offdutydate4 = value;
    }

    /**
     * ��ȡjobnode���Ե�ֵ��
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
     * ����jobnode���Ե�ֵ��
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
     * ��ȡarvTrain���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArvTrain() {
        return arvTrain;
    }

    /**
     * ����arvTrain���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArvTrain(String value) {
        this.arvTrain = value;
    }

    /**
     * ��ȡarvStation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArvStation() {
        return arvStation;
    }

    /**
     * ����arvStation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArvStation(String value) {
        this.arvStation = value;
    }

    /**
     * ��ȡarvTime���Ե�ֵ��
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
     * ����arvTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArvTime(XMLGregorianCalendar value) {
        this.arvTime = value;
    }

    /**
     * ��ȡdriver1OnDutyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver1OnDutyCode() {
        return driver1OnDutyCode;
    }

    /**
     * ����driver1OnDutyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver1OnDutyCode(String value) {
        this.driver1OnDutyCode = value;
    }

    /**
     * ��ȡdriver2OnDutyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver2OnDutyCode() {
        return driver2OnDutyCode;
    }

    /**
     * ����driver2OnDutyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver2OnDutyCode(String value) {
        this.driver2OnDutyCode = value;
    }

    /**
     * ��ȡdriver3OnDutyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver3OnDutyCode() {
        return driver3OnDutyCode;
    }

    /**
     * ����driver3OnDutyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver3OnDutyCode(String value) {
        this.driver3OnDutyCode = value;
    }

    /**
     * ��ȡdriver4OnDutyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver4OnDutyCode() {
        return driver4OnDutyCode;
    }

    /**
     * ����driver4OnDutyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver4OnDutyCode(String value) {
        this.driver4OnDutyCode = value;
    }

    /**
     * ��ȡdriver1OffDutyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver1OffDutyCode() {
        return driver1OffDutyCode;
    }

    /**
     * ����driver1OffDutyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver1OffDutyCode(String value) {
        this.driver1OffDutyCode = value;
    }

    /**
     * ��ȡdriver2OffDutyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver2OffDutyCode() {
        return driver2OffDutyCode;
    }

    /**
     * ����driver2OffDutyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver2OffDutyCode(String value) {
        this.driver2OffDutyCode = value;
    }

    /**
     * ��ȡdriver3OffDutyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver3OffDutyCode() {
        return driver3OffDutyCode;
    }

    /**
     * ����driver3OffDutyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver3OffDutyCode(String value) {
        this.driver3OffDutyCode = value;
    }

    /**
     * ��ȡdriver4OffDutyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver4OffDutyCode() {
        return driver4OffDutyCode;
    }

    /**
     * ����driver4OffDutyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver4OffDutyCode(String value) {
        this.driver4OffDutyCode = value;
    }

    /**
     * ��ȡdriver1Team���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver1Team() {
        return driver1Team;
    }

    /**
     * ����driver1Team���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver1Team(String value) {
        this.driver1Team = value;
    }

    /**
     * ��ȡdriver2Team���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver2Team() {
        return driver2Team;
    }

    /**
     * ����driver2Team���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver2Team(String value) {
        this.driver2Team = value;
    }

    /**
     * ��ȡdriver3Team���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver3Team() {
        return driver3Team;
    }

    /**
     * ����driver3Team���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver3Team(String value) {
        this.driver3Team = value;
    }

    /**
     * ��ȡdriver4Team���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver4Team() {
        return driver4Team;
    }

    /**
     * ����driver4Team���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver4Team(String value) {
        this.driver4Team = value;
    }

    /**
     * ��ȡdriver1Group���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver1Group() {
        return driver1Group;
    }

    /**
     * ����driver1Group���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver1Group(String value) {
        this.driver1Group = value;
    }

    /**
     * ��ȡdriver2Group���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver2Group() {
        return driver2Group;
    }

    /**
     * ����driver2Group���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver2Group(String value) {
        this.driver2Group = value;
    }

    /**
     * ��ȡdriver3Group���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver3Group() {
        return driver3Group;
    }

    /**
     * ����driver3Group���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver3Group(String value) {
        this.driver3Group = value;
    }

    /**
     * ��ȡdriver4Group���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver4Group() {
        return driver4Group;
    }

    /**
     * ����driver4Group���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver4Group(String value) {
        this.driver4Group = value;
    }

    /**
     * ��ȡtaskName���Ե�ֵ��
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
     * ����taskName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskName(String value) {
        this.taskName = value;
    }

}
