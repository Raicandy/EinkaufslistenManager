/*
@author Daniel Meerwald

Diese Klasse ist zum Anzeigen der Einkaufslistenliste. Da List<Einkaufsliste> selbst keine Annotationen besitzt, hilft diese Klasse aus.
Sie stellt die nötigen Annotationen zur Verfügung und übernimmt die übergebene Liste, um diese dann wieder zurück zu geben. 
Die Klasse findet Verwendung in der getListenliste() Methode des Clients.
*/

package Einkaufsliste.core;
 
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement; 
    
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ListenlisteRoot")
public class ListenListenhelper {

    @XmlElement(name = "Listenliste")
    private List<Einkaufsliste> listen = new ArrayList<Einkaufsliste>();
    
   
    
    public ListenListenhelper() {}
    
    
    
    public ListenListenhelper(List<Einkaufsliste> listen) {
        this.listen = listen;
    }
    
    
    
    public List<Einkaufsliste> getListen() {
        return listen;
    }
    
    
    
    public void setListen(List<Einkaufsliste> listen) {
        this.listen = listen;
    }   
}

