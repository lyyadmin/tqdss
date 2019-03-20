
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getDriverDutyRecordByWorkNoAndTimeStr complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getDriverDutyRecordByWorkNoAndTimeStr">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="workNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "getDriverDutyRecordByWorkNoAndTimeStr", propOrder = {
    "workNo",
    "beginTimeStr",
    "endTimeStr"
})
public class GetDriverDutyRecordByWorkNoAndTimeStr {

    protected int workNo;
    protected String beginTimeStr;
    protected String endTimeStr;

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
     * 获取beginTimeStr属性的值。
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
     * 设置beginTimeStr属性的值。
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
     * 获取endTimeStr属性的值。
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
     * 设置endTimeStr属性的值。
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
