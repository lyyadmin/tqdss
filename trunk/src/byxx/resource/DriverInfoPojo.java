
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>driverInfoPojo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="driverInfoPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="driverid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="section" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="workno" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="personname" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="homephone" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="handphone" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="postname" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="workplace" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="address" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="chedui" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="zhidaozu" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="education" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="birthdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="idcardnum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="workcardnum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="graduateschool" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="graduatenum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="techlevel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="maritalstatus" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="zip" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="homeaddress" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="inworktime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="sex" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="workplaceName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "driverInfoPojo")
public class DriverInfoPojo {

    @XmlAttribute(name = "driverid", required = true)
    protected int driverid;
    @XmlAttribute(name = "section")
    protected String section;
    @XmlAttribute(name = "workno", required = true)
    protected int workno;
    @XmlAttribute(name = "personname")
    protected String personname;
    @XmlAttribute(name = "homephone")
    protected String homephone;
    @XmlAttribute(name = "handphone")
    protected String handphone;
    @XmlAttribute(name = "postname")
    protected String postname;
    @XmlAttribute(name = "workplace")
    protected String workplace;
    @XmlAttribute(name = "address")
    protected String address;
    @XmlAttribute(name = "chedui")
    protected String chedui;
    @XmlAttribute(name = "zhidaozu")
    protected String zhidaozu;
    @XmlAttribute(name = "education", required = true)
    protected int education;
    @XmlAttribute(name = "birthdate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar birthdate;
    @XmlAttribute(name = "idcardnum")
    protected String idcardnum;
    @XmlAttribute(name = "nation")
    protected String nation;
    @XmlAttribute(name = "workcardnum")
    protected String workcardnum;
    @XmlAttribute(name = "graduateschool")
    protected String graduateschool;
    @XmlAttribute(name = "graduatenum")
    protected String graduatenum;
    @XmlAttribute(name = "techlevel")
    protected String techlevel;
    @XmlAttribute(name = "maritalstatus", required = true)
    protected int maritalstatus;
    @XmlAttribute(name = "zip")
    protected String zip;
    @XmlAttribute(name = "homeaddress")
    protected String homeaddress;
    @XmlAttribute(name = "inworktime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar inworktime;
    @XmlAttribute(name = "sex", required = true)
    protected int sex;
    @XmlAttribute(name = "workplaceName")
    protected String workplaceName;

    /**
     * ��ȡdriverid���Ե�ֵ��
     * 
     */
    public int getDriverid() {
        return driverid;
    }

    /**
     * ����driverid���Ե�ֵ��
     * 
     */
    public void setDriverid(int value) {
        this.driverid = value;
    }

    /**
     * ��ȡsection���Ե�ֵ��
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
     * ����section���Ե�ֵ��
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
     * ��ȡworkno���Ե�ֵ��
     * 
     */
    public int getWorkno() {
        return workno;
    }

    /**
     * ����workno���Ե�ֵ��
     * 
     */
    public void setWorkno(int value) {
        this.workno = value;
    }

    /**
     * ��ȡpersonname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonname() {
        return personname;
    }

    /**
     * ����personname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonname(String value) {
        this.personname = value;
    }

    /**
     * ��ȡhomephone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomephone() {
        return homephone;
    }

    /**
     * ����homephone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomephone(String value) {
        this.homephone = value;
    }

    /**
     * ��ȡhandphone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandphone() {
        return handphone;
    }

    /**
     * ����handphone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandphone(String value) {
        this.handphone = value;
    }

    /**
     * ��ȡpostname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostname() {
        return postname;
    }

    /**
     * ����postname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostname(String value) {
        this.postname = value;
    }

    /**
     * ��ȡworkplace���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkplace() {
        return workplace;
    }

    /**
     * ����workplace���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkplace(String value) {
        this.workplace = value;
    }

    /**
     * ��ȡaddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * ����address���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * ��ȡchedui���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChedui() {
        return chedui;
    }

    /**
     * ����chedui���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChedui(String value) {
        this.chedui = value;
    }

    /**
     * ��ȡzhidaozu���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZhidaozu() {
        return zhidaozu;
    }

    /**
     * ����zhidaozu���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZhidaozu(String value) {
        this.zhidaozu = value;
    }

    /**
     * ��ȡeducation���Ե�ֵ��
     * 
     */
    public int getEducation() {
        return education;
    }

    /**
     * ����education���Ե�ֵ��
     * 
     */
    public void setEducation(int value) {
        this.education = value;
    }

    /**
     * ��ȡbirthdate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthdate() {
        return birthdate;
    }

    /**
     * ����birthdate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthdate(XMLGregorianCalendar value) {
        this.birthdate = value;
    }

    /**
     * ��ȡidcardnum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdcardnum() {
        return idcardnum;
    }

    /**
     * ����idcardnum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdcardnum(String value) {
        this.idcardnum = value;
    }

    /**
     * ��ȡnation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNation() {
        return nation;
    }

    /**
     * ����nation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNation(String value) {
        this.nation = value;
    }

    /**
     * ��ȡworkcardnum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkcardnum() {
        return workcardnum;
    }

    /**
     * ����workcardnum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkcardnum(String value) {
        this.workcardnum = value;
    }

    /**
     * ��ȡgraduateschool���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGraduateschool() {
        return graduateschool;
    }

    /**
     * ����graduateschool���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGraduateschool(String value) {
        this.graduateschool = value;
    }

    /**
     * ��ȡgraduatenum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGraduatenum() {
        return graduatenum;
    }

    /**
     * ����graduatenum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGraduatenum(String value) {
        this.graduatenum = value;
    }

    /**
     * ��ȡtechlevel���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechlevel() {
        return techlevel;
    }

    /**
     * ����techlevel���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechlevel(String value) {
        this.techlevel = value;
    }

    /**
     * ��ȡmaritalstatus���Ե�ֵ��
     * 
     */
    public int getMaritalstatus() {
        return maritalstatus;
    }

    /**
     * ����maritalstatus���Ե�ֵ��
     * 
     */
    public void setMaritalstatus(int value) {
        this.maritalstatus = value;
    }

    /**
     * ��ȡzip���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    /**
     * ����zip���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZip(String value) {
        this.zip = value;
    }

    /**
     * ��ȡhomeaddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeaddress() {
        return homeaddress;
    }

    /**
     * ����homeaddress���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeaddress(String value) {
        this.homeaddress = value;
    }

    /**
     * ��ȡinworktime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInworktime() {
        return inworktime;
    }

    /**
     * ����inworktime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInworktime(XMLGregorianCalendar value) {
        this.inworktime = value;
    }

    /**
     * ��ȡsex���Ե�ֵ��
     * 
     */
    public int getSex() {
        return sex;
    }

    /**
     * ����sex���Ե�ֵ��
     * 
     */
    public void setSex(int value) {
        this.sex = value;
    }

    /**
     * ��ȡworkplaceName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkplaceName() {
        return workplaceName;
    }

    /**
     * ����workplaceName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkplaceName(String value) {
        this.workplaceName = value;
    }

}
