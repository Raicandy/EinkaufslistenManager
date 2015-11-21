/*
 @author Daniel Meerwald

 Die Klasse stellt die Implementierung des EinkaufslisteService Interface dar. 
 Alle vorher deklarierten Methoden finden hier ihre Implementierung, um die Einkaufslisten zu verwalten. 
 Dies geschieht über die Liste Einkaufslisten die eine Liste aller Einkaufslisten darstellt.
 
*/
package Einkaufsliste.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class EinkaufslisteServiceImpl implements EinkaufslisteService {

    private List<Einkaufsliste> Einkaufslisten = new ArrayList<>();
    
    
    @Override
    public Einkaufsliste addEinkaufsliste(String Name) {

        Einkaufsliste Liste = new Einkaufsliste(Name);
        Einkaufslisten.add(Liste);
        return Liste;
    }
    
   
    
    public void remEinkaufsliste(String Name) {

        int i = 0;
        boolean indikator = false;

        while (i < Einkaufslisten.size() && indikator == false) { //Suche bis zum Ende der Liste.

            if (Einkaufslisten.get(i).getName() == null ? Name == null : Einkaufslisten.get(i).getName().equals(Name)) {
                Einkaufslisten.remove(i);
                indikator = true; //Ende der Methode, wenn das Objekt gefunden wurde.
            } else {
                i++; //Andernfalls wird das nächste Element vergleichen.
            }
        }
    }
    
    

    @Override
    public Einkaufsliste getEinkaufsliste(String Name) {

        int i = 0;
        boolean indikator = false;

        while (i < Einkaufslisten.size() && indikator == false) { 

            if (Einkaufslisten.get(i).getName().equals(Name)) { 
                indikator = true;
            } else {
                i++; 
            }
        }

        if (indikator == false) {
            return null;   //Erzeugt Fehlercode, falls eine unzulässige Anfrage gestellt wurde.
        } else {
            return Einkaufslisten.get(i);
        }
    }
    
    

    @Override
    public List<Einkaufsliste> getListenliste() {

        return Einkaufslisten;
    }

    
    
    @Override
    public void save(String pfad) {
     
       
        ListenListenhelper Gesamtlistehelper = new ListenListenhelper(Einkaufslisten);
	  try { 

		File file = new File(pfad);
		JAXBContext jaxbContext = JAXBContext.newInstance(ListenListenhelper.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller(); //Marshaller erstellen
		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(Gesamtlistehelper, file); //XML im pfad speichern
		jaxbMarshaller.marshal(Gesamtlistehelper, System.out);

	      } catch (JAXBException e) {
		e.printStackTrace();
	      }
    }

    
    
    @Override
    public void open(String pfad) { //Zu dem Array hinzufügen EinkauflisteaddAll()
        ListenListenhelper liste = new ListenListenhelper();
    try {

		File file = new File(pfad);
		JAXBContext jaxbContext = JAXBContext.newInstance(ListenListenhelper.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		liste = (ListenListenhelper) jaxbUnmarshaller.unmarshal(file);

	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
    
          List<Einkaufsliste> Einkaufslisten2 = new ArrayList<>();
          Einkaufslisten2 = liste.getListen();
          
          Einkaufslisten.clear();
          Einkaufslisten = Einkaufslisten2;
    }
}
