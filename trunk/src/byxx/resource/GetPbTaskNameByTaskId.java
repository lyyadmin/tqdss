
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getPbTaskNameByTaskId complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getPbTaskNameByTaskId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taskdicid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPbTaskNameByTaskId", propOrder = {
    "taskdicid"
})
public class GetPbTaskNameByTaskId {

    protected int taskdicid;

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

}
