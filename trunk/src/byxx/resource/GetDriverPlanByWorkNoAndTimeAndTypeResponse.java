
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getDriverPlanByWorkNoAndTimeAndTypeResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getDriverPlanByWorkNoAndTimeAndTypeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://resource.byxx/}driverPlanPojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDriverPlanByWorkNoAndTimeAndTypeResponse", propOrder = {
    "_return"
})
public class GetDriverPlanByWorkNoAndTimeAndTypeResponse {

    @XmlElement(name = "return")
    protected DriverPlanPojo _return;

    /**
     * ��ȡreturn���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link DriverPlanPojo }
     *     
     */
    public DriverPlanPojo getReturn() {
        return _return;
    }

    /**
     * ����return���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link DriverPlanPojo }
     *     
     */
    public void setReturn(DriverPlanPojo value) {
        this._return = value;
    }

}