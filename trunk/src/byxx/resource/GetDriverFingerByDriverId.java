
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getDriverFingerByDriverId complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getDriverFingerByDriverId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="driverId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDriverFingerByDriverId", propOrder = {
    "driverId"
})
public class GetDriverFingerByDriverId {

    protected int driverId;

    /**
     * 获取driverId属性的值。
     * 
     */
    public int getDriverId() {
        return driverId;
    }

    /**
     * 设置driverId属性的值。
     * 
     */
    public void setDriverId(int value) {
        this.driverId = value;
    }

}
