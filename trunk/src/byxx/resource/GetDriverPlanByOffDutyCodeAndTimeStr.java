
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getDriverPlanByOffDutyCodeAndTimeStr complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getDriverPlanByOffDutyCodeAndTimeStr">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offDutyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beginTimeStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endTimeStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDriverPlanByOffDutyCodeAndTimeStr", propOrder = {
    "offDutyCode",
    "beginTimeStr",
    "endTimeStr"
})
public class GetDriverPlanByOffDutyCodeAndTimeStr {

    protected String offDutyCode;
    protected String beginTimeStr;
    protected String endTimeStr;

    /**
     * ��ȡoffDutyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffDutyCode() {
        return offDutyCode;
    }

    /**
     * ����offDutyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffDutyCode(String value) {
        this.offDutyCode = value;
    }

    /**
     * ��ȡbeginTimeStr���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeginTimeStr() {
        return beginTimeStr;
    }

    /**
     * ����beginTimeStr���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeginTimeStr(String value) {
        this.beginTimeStr = value;
    }

    /**
     * ��ȡendTimeStr���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTimeStr() {
        return endTimeStr;
    }

    /**
     * ����endTimeStr���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTimeStr(String value) {
        this.endTimeStr = value;
    }

}
