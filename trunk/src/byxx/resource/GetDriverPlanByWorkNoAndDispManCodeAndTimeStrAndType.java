
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getDriverPlanByWorkNoAndDispManCodeAndTimeStrAndType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getDriverPlanByWorkNoAndDispManCodeAndTimeStrAndType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="workNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dispmancode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDriverPlanByWorkNoAndDispManCodeAndTimeStrAndType", propOrder = {
    "workNo",
    "dispmancode",
    "timeStr",
    "type"
})
public class GetDriverPlanByWorkNoAndDispManCodeAndTimeStrAndType {

    protected int workNo;
    protected String dispmancode;
    protected String timeStr;
    protected int type;

    /**
     * ��ȡworkNo���Ե�ֵ��
     * 
     */
    public int getWorkNo() {
        return workNo;
    }

    /**
     * ����workNo���Ե�ֵ��
     * 
     */
    public void setWorkNo(int value) {
        this.workNo = value;
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
     * ��ȡtimeStr���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeStr() {
        return timeStr;
    }

    /**
     * ����timeStr���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStr(String value) {
        this.timeStr = value;
    }

    /**
     * ��ȡtype���Ե�ֵ��
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * ����type���Ե�ֵ��
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

}
