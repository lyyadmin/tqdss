
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getLocoPlanByWorkNo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getLocoPlanByWorkNo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="workNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLocoPlanByWorkNo", propOrder = {
    "workNo"
})
public class GetLocoPlanByWorkNo {

    protected int workNo;

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

}
