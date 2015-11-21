/*
@author Daniel Meerwald

Diese Klasse ist zum Anzeigen der Produktliste Da List<Produkt> selbst keine Annotationen besitzt, hilft die hier Klasse aus.
Die Klasse stellt die nötigen Annotationen zur Verfügung und übernimmt die übergebene Liste, um diese dann wieder zurück zu geben. 
Sie findet Verwendung in der getProduktliste() Methode des Clients.
*/

package Einkaufsliste.core;
 
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ProduktlisteRoot")
public class Listenhelper {
   
   
    
    @XmlElement(name = "Produktliste")
    private List<Produkt> produkte = new ArrayList<Produkt>();
     
    
    
    public Listenhelper() {}
  
    
    
    public Listenhelper(List<Produkt> produkte) {
        this.produkte = produkte;
    }
 
    
    
    public List<Produkt> getProdukte() {
        return produkte;
    }
 
    
    
    public void setProdukte(List<Produkt> produkte) {
        this.produkte = produkte;
    }   
}

