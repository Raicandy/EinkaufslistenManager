/*
@author Daniel Meerwald

Diese Klasse ist zum Anzeigen der Kategorieliste. Da List<String> selbst keine Annotationen besitzt, hilft diese Klasse aus 
und stellt die nötigen Annotationen zur Verfügung. Sie übernimmt die übergebene Liste, um diese dann wieder zurück zu geben. 
Die Klasse findet Verwendung in der getkategorieprodukte() Methode des Clients.
*/

package Einkaufsliste.core;
 
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
    
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "KategorielisteRoot")
public class Stringlisthelper {

    @XmlElement(name = "Kategorieliste")
    private List<String> kategorien = new ArrayList<String>();
     

    public Stringlisthelper() {}
    
    
    public Stringlisthelper(List<String> kategorien) {
        this.kategorien = kategorien;
    }
 

    public List<String> getKategorieen() {
        return kategorien;
    }
 

    public void setKategorieen(List<String> kategorien) {
        this.kategorien = kategorien;
    }   
    
}