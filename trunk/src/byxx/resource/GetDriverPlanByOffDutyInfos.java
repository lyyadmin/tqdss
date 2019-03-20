
package byxx.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getDriverPlanByOffDutyInfos complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getDriverPlanByOffDutyInfos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="workno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="arvTimeStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arvTrain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arvStation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loco" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDriverPlanByOffDutyInfos", propOrder = {
    "workno",
    "arvTimeStr",
    "arvTrain",
    "arvStation",
    "model",
    "loco"
})
public class GetDriverPlanByOffDutyInfos {

    protected int workno;
    protected String arvTimeStr;
    protected String arvTrain;
    protected String arvStation;
    protected String model;
    protected int loco;

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
     * ��ȡarvTimeStr���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArvTimeStr() {
        return arvTimeStr;
    }

    /**
     * ����arvTimeStr���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArvTimeStr(String value) {
        this.arvTimeStr = value;
    }

    /**
     * ��ȡarvTrain���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArvTrain() {
        return arvTrain;
    }

    /**
     * ����arvTrain���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArvTrain(String value) {
        this.arvTrain = value;
    }

    /**
     * ��ȡarvStation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArvStation() {
        return arvStation;
    }

    /**
     * ����arvStation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArvStation(String value) {
        this.arvStation = value;
    }

    /**
     * ��ȡmodel���Ե�ֵ��
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
     * ����model���Ե�ֵ��
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
     * ��ȡloco���Ե�ֵ��
     * 
     */
    public int getLoco() {
        return loco;
    }

    /**
     * ����loco���Ե�ֵ��
     * 
     */
    public void setLoco(int value) {
        this.loco = value;
    }

}
