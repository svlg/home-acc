package home.acc.XML;

import home.acc.Entity.Operation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType
@XmlRootElement
public class Operations {
    
    @XmlElements(@XmlElement(name="operation", type=Operation.class))
    List operations;
    
    public void setOperations(List operations) {
        this.operations = operations;
    }
}
