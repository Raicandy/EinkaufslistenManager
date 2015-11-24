/*
 @author Daniel Meerwald

 Diese Klasse bedient sich der Methoden der Client Klasse, um alle Funktionen des Clients zu Testen.
 */
package Einkaufsliste.client;

import Einkaufsliste.core.Einkaufsliste;
import Einkaufsliste.core.Produkt;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 *
 * @author raicandy
 */
public class Tester {
    
    public static void main(String[] args) throws IOException, JAXBException { //Testen der Methoden des Clients.

        Client Tester = new Client("http://localhost:8080");

        //EKListen erstellen.
        System.out.println("###################### Erstellen der Listen ######################");
        Tester.addEinkaufsliste("Testliste");
        System.out.println("Testliste erstellt!");
        Tester.addEinkaufsliste("Testliste2");
        System.out.println("Testliste2 erstellt!");

        //Produkte hinzufügen.
        System.out.println("###################### Produkte hinzufügen ######################");
        Tester.addProdukt("Testliste", "Milch", "Milchprodukte", "Kaufland", (float) 0.69, (int) 4);
        System.out.println("Testliste: Produkt Milch hinzugefügt");
        Tester.addProdukt("Testliste", "Kaese", "Milchprodukte", "Kaufland", (float) 2.79, (int) 2);
        System.out.println("Testliste: Produkt Käse hinzugefügt");
        Tester.addProdukt("Testliste", "Brot", "Mehlprodukte", "Kaufland", (float) 0.80, (int) 1);
        System.out.println("Testliste: Produkt Brot hinzugefügt");

        Tester.addProdukt("Testliste2", "Wasser", "Getraenke", "Aldi", (float) 1.99, (int) 2);
        System.out.println("Testliste2: Produkt Wasser hinzugefügt");
        Tester.addProdukt("Testliste2", "Cola", "Milchprodukte", "Aldi", (float) 0.79, (int) 6);
        System.out.println("Testliste2: Produkt Cola hinzugefügt");

        //Kategorien anzeigen
        System.out.println("###################### Kategorien anzeigen ######################");
        List<String> Liste = Tester.getKategorieliste("Testliste");
        System.out.println(Liste);
        System.out.println("Alle Milchprodukte anzeigen:");
        List<Produkt> kategorieliste = Tester.getKategorieprodukte("Testliste", "Milchprodukte");
        System.out.println(kategorieliste);
        
        //Listen Anzeigen.
        System.out.println("###################### Listen anzeigen ######################");
        List<Produkt> produktliste = Tester.getProduktliste("Testliste");
        System.out.println("Testliste:");
        Tester.printProduktliste(produktliste);

        List<Produkt> produktliste2 = Tester.getProduktliste("Testliste2");
        System.out.println("Testliste2:");
        Tester.printProduktliste(produktliste2);
        
        System.out.println("###################### Listen speichern ######################");
        Tester.save("/home/raicandy/Desktop/Testlisten.xml");
        
        System.out.println("###################### Listen oeffnen ######################");
        Tester.open("C:\\Testliste.xml.xml");

        //Liste der Listen anzeigen.
        System.out.println("###################### Listenliste anzeigen ######################");
        List<Einkaufsliste> Listenliste = Tester.getListenListe();
        Tester.printListenliste(Listenliste);
        
        //Produkt entfernen.
        System.out.println("###################### Produkt entfernen ######################");
        Tester.remProdukt("Testliste", "Milch");
        System.out.println("Milch wurde aus Testliste entfernt!");
        System.out.println("Testliste:");
        produktliste = Tester.getProduktliste("Testliste");
        Tester.printProduktliste(produktliste);

        //Einkaufliste entfernen.
        System.out.println("###################### Liste entfernen ######################");
        Tester.remEinkaufsliste("Testliste");

        //Liste der Listen anzeigen.
        System.out.println("###################### Listenliste anzeigen ######################");
        Listenliste = Tester.getListenListe();
        Tester.printListenliste(Listenliste);

        System.out.println("###################### Listen oeffnen ######################");
        Listenliste = Tester.getListenListe();
        Tester.printListenliste(Listenliste);
        Tester.open("/home/raicandy/Desktop/Testlisten.xml");
        Listenliste = Tester.getListenListe();
        Tester.printListenliste(Listenliste);
    }
    
}
