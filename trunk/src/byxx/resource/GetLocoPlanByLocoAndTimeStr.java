
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getLocoPlanByLocoAndTimeStr complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getLocoPlanByLocoAndTimeStr">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loco" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="timeStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLocoPlanByLocoAndTimeStr", propOrder = {
    "model",
    "loco",
    "timeStr"
})
public class GetLocoPlanByLocoAndTimeStr {

    protected String model;
    protected int loco;
    protected String timeStr;

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
     * 获取timeStr属性的值。
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
     * 设置timeStr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStr(String value) {
        this.timeStr = value;
    }

}
