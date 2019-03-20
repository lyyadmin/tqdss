
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getDriverPlanByWorkNoAndDispManCodeAndType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getDriverPlanByWorkNoAndDispManCodeAndType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="workNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dispmancode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "getDriverPlanByWorkNoAndDispManCodeAndType", propOrder = {
    "workNo",
    "dispmancode",
    "type"
})
public class GetDriverPlanByWorkNoAndDispManCodeAndType {

    protected int workNo;
    protected String dispmancode;
    protected int type;

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
     * 获取type属性的值。
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

}
