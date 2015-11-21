/*
 @author Daniel Meerwald

 Die Klasse stellt einen benutzbaren Client für den Einkaufslistenmanager dar, 
 der die Bedienung durch ein texbasiertes Interface vereinfacht. Der Client erstellt
 aus den eingegebenen Informationen die Methoden von ClientInterface, die 
 die Client Klasse in HTTP Anfragen umwandelt.
 */
package Einkaufsliste.client;

import Einkaufsliste.core.Einkaufsliste;
import Einkaufsliste.core.Produkt;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.JAXBException;

public class ClientInterface{
    
    public static void main(String[] args) throws IOException, JAXBException { 
        
        Client client;
        try (Scanner in = new Scanner(System.in)) {
            String eingabe = null;
            String pfad = null;
            boolean token;
            boolean t2;
            boolean t3;
            List<Einkaufsliste> Listenliste;
            List<Produkt> produktliste;
            
            System.out.println("Willkommen beim Simple List Manager!");
            
            System.out.println("Befindet sich der Server auf dem selben PC?");
            System.out.println("(Y/N)?");
            eingabe = in.nextLine();
            
            if("N".equals(eingabe) && "n".equals(eingabe)){
                System.out.println("Bitte geben Sie die URL des Servers ein:");
                eingabe = in.nextLine();
                client = new Client("eingabe");
            }
            else{
                client = new Client("http://localhost:8080");
            }
            
            System.out.println("Soll das Pogramm eine vorhandene Listendatenbank verwenden und laden??");
            
            token = false;
            while(token != true){
                
                System.out.println("(Y/N)?");
                
                eingabe = in.nextLine();
                
                if("Y".equals(eingabe)){
                    
                    while(token != true){
                        System.out.println("Bitte geben Sie Ihren Pfad ein.");
                        
                        eingabe = in.nextLine();
                        pfad = eingabe;
                        
                        System.out.println("Pfad ist: "+ pfad);
                        System.out.println("Ist das korrekt? (Y/N)?");
                        
                        eingabe = in.nextLine();
                        
                        if("Y".equals(eingabe)){
                            
                            System.out.println("Listen werden geladen...");
                            client.open(pfad);
                            System.out.println("Folgende Listen wurden geladen: ");
                            Listenliste = client.getListenListe();
                            client.printListenliste(Listenliste);
                            token = true;
                            
                        }
                        if("N".equals(eingabe)){
                            System.out.println("Pfadkorrektur.");
                        }
                    }
                }
                
                if("N".equals(eingabe)){
                    
                    System.out.println("Soll das Programm Listen im Standardpfad speichern oder wollen Sie einen eigenen Verwenden?");
                    System.out.println("(Standard/Eigenen)?");
                    
                    while(token != true){
                        
                        
                        eingabe = in.nextLine();
                        if("Standard".equals(eingabe)){
                            
                            System.out.println("Benutzen Sie ein Linux oder Windows PC?");
                            System.out.println("(Linux/Windows)?");
                            
                            while(token != true){
                                
                                eingabe = in.nextLine();
                                
                                if("Linux".equals(eingabe)){
                                    pfad = "/home/raicandy/Desktop/Einkauflisten.xml";
                                    System.out.println("Speicherpfad ist nun: "+ pfad);
                                    token = true;
                                }
                                if("Windows".equals(eingabe)){
                                    pfad = "C:\\Einkaufslisten.xml";
                                    System.out.println("Speicherpfad ist nun: "+ pfad);
                                    token = true;
                                }
                                if(!"Windows".equals(eingabe) && !"Linux".equals(eingabe)){
                                    System.out.println("Bitte geben sie Windows oder Linux ein.");
                                    System.out.println("(Linux/Windows)?");
                                }
                                
                            }
                        }
                        if("Eigenen".equals(eingabe)){
                            
                            while(token != true){
                                System.out.println("Bitte geben Sie Ihren Pfad ein.");
                                
                                eingabe = in.nextLine();
                                pfad = eingabe;
                                
                                System.out.println("Pfad ist: "+ eingabe);
                                System.out.println("Ist das so korrekt? (Y/N)?");
                                
                                eingabe = in.nextLine();
                                
                                if("Y".equals(eingabe)){
                                    token = true;
                                }
                                if("N".equals(eingabe)){
                                    System.out.println("Pfadkorrektur.");
                                }
                            }
                            
                        }
                    }
                    
                }
            }
            
            t2 = false;
            while(t2 != true){
                //Menue für das Managen der Listen:
                System.out.println("########################################################");
                System.out.println("Was moechten Sie tun? Folgende Optionen stehen zur Wahl:");
                System.out.println("********************************************************");
                System.out.println("E:    Neue Einkaufliste erstellen");
                System.out.println("L:    Bestehende Liste Loeschen");
                System.out.println("A:    Alle Listen anzeigen");
                System.out.println("I:    Inhalt einer bestimmten Liste anzeigen");
                System.out.println("V:    Bestimmte Liste Verwalten");
                System.out.println("********************************************************");
                System.out.println("Back:     Das Menue verlassen und zum vorherigen Menue zurueckkehren");
                System.out.println("SaveExit: Die Listen speichern und das Programm verlassen");
                System.out.println("########################################################");
                
                token = false;
                while(token != true){
                    
                    eingabe = in.nextLine();
                    
                    if("E".equals(eingabe)|"e".equals(eingabe)){
                        
                        System.out.println("Bitte geben Sie den Namen Ihrer neuen Liste ein.");
                        eingabe = in.nextLine();
                        client.addEinkaufsliste(eingabe);
                        System.out.println("Liste "+ eingabe + " wurde erstellt!");
                        token = true;
                    }   
                    
                    
                    if("L".equals(eingabe)|"l".equals(eingabe)){
                        
                        System.out.println("Bitte geben Sie den Namen der Liste ein, die geloescht werden soll.");
                        eingabe = in.nextLine();
                        client.remEinkaufsliste(eingabe);
                        System.out.println("Liste "+ eingabe + " wurde geloescht!");
                        token = true;

                    }
                    
                    
                    if("A".equals(eingabe)|"a".equals(eingabe)){
                        
                        System.out.println("Folgende Listen sind geladen: ");
                        Listenliste = client.getListenListe();
                        client.printListenliste(Listenliste);
                        token = true;

                    }
                    
                    
                    if("I".equals(eingabe)|"i".equals(eingabe)){
                        
                        while(token != true){
                            System.out.println("Bitte geben Sie den Namen der Liste ein, deren Inhalt Sie sehen wollen..");
                            eingabe = in.nextLine();
                            produktliste = client.getProduktliste(eingabe);
                            client.printProduktliste(produktliste);
                            System.out.println("Eine weitere Liste aufrufen?");
                            System.out.println("(Y/N)");
                            eingabe = in.nextLine();
                            
                            if("N".equals(eingabe)){
                                
                                token = true;
                            }
                        }
                        
                    }
                    
                    
                    if("SaveExit".equals(eingabe)){
                        
                        client.save(pfad);
                        token = true;
                        t2 = true;

                    }
                    
                    
                    if("Exit".equals(eingabe)){
                        
                        token = true;
                        t2 = true;

                    }
                    
                    
                    if("V".equals(eingabe)|"v".equals(eingabe)){

                        String verwalter = null;
                        
                        
                        token = false;
                        while(token != true){
                            
                            System.out.println("Bitte geben Sie den Namen der Liste ein, die Sie verwalten wollen.");
                            verwalter = in.nextLine();
                                            
                            System.out.println("Liste ist: "+ verwalter);
                            System.out.println("Ist das korrekt? (Y/N)?");

                            eingabe = in.nextLine();

                            if("Y".equals(eingabe)){
                                token = true;
                            }
                            if("N".equals(eingabe)){
                                System.out.println("Korrektur.");
                            }
                        }

                        t3 = false;
                        while(t3 != true){
                            //Menue für eine Liste:
                            System.out.println("########################################################");
                            System.out.println("Um die gewaehlte Liste zu verwalten gibt es folgende Moeglichkeiten:");
                            System.out.println("********************************************************");
                            System.out.println("I:    Inhalt dieser Liste anzeigen");
                            System.out.println("P:    Ein Produkt zur Liste hinzufuegen");
                            System.out.println("Q:    Ein Produkt schnell zur Liste hinzufuegen (nur Name und Anzahl)");
                            System.out.println("L:    Ein Produkt loeschen");
                            System.out.println("B:    Ein Produkt bearbeiten");
                            System.out.println("A:    Alle Kategorien der Liste anzeigen");
                            System.out.println("K:    Alle Produkte einer Kategorie anzeigen");
                            System.out.println("********************************************************");
                            System.out.println("Back:     Das Menue verlassen und zum vorherigen Menue zurueckkehren");
                            System.out.println("SaveExit: Die Listen speichern und das Programm verlassen");
                            System.out.println("Exit:     Das Programm verlassen, ohne zu speichern");
                            System.out.println("########################################################");
                            
                            token = false;
                            while(token != true){
                                
                                eingabe = in.nextLine();
                                
                                if("P".equals(eingabe)|"p".equals(eingabe)){
                                    
                                    String pname = null;
                                    String kategorie = null;
                                    String markt = null;
                                    float preis = 0;
                                    int anzahl = 0;
                                    
                                    System.out.println("Produktname:");
                                    pname = in.nextLine();
                                    System.out.println("Kategorie");
                                    kategorie = in.nextLine();
                                    System.out.println("Markt");
                                    markt = in.nextLine();
                                    System.out.println("Preis");
                                    preis = in.nextFloat();
                                    System.out.println("Anzahl");
                                    anzahl = in.nextInt();
                                    
                                    client.addProdukt(verwalter, pname, kategorie, markt, preis, anzahl);
                                    
                                    token = true;
                                }
                                
                                if("Q".equals(eingabe)|"q".equals(eingabe)){
                                    
                                    String pname = null;
                                    String kategorie = null;
                                    String markt = null;
                                    float preis = 0;
                                    int anzahl = 0;
                                    
                                    System.out.println("Produktname:");
                                    pname = in.nextLine();
                                    System.out.println("Anzahl");
                                    anzahl = in.nextInt();
                                    
                                    client.addProdukt(verwalter, pname, kategorie, markt, preis, anzahl);
                                    
                                    token = true;
                                }
                                
                                
                                if("L".equals(eingabe)|"l".equals(eingabe)){
                                    
                                    System.out.println("Bitte geben Sie den Namen des zu loeschenden Produktes ein.");
                                    eingabe = in.nextLine();
                                    client.remProdukt(verwalter, eingabe);
                                    token = true;

                                }
                                
                                
                                if("B".equals(eingabe)|"b".equals(eingabe)){
                                    
                                    
                                    String pname = null;
                                    String kategorie = null;
                                    String markt = null;
                                    float preis = 0;
                                    int anzahl = 0;
                                    
                                    System.out.println("Bitte geben Sie an, welches Produkt Sie bearbeiten wollen.");
                                    System.out.println("Produktname:");
                                    pname = in.nextLine();
                                    System.out.println("Bitte geben Sie nun die neuen Werte für " + pname + " an.");
                                    System.out.println("Kategorie");
                                    kategorie = in.nextLine();
                                    System.out.println("Markt");
                                    markt = in.nextLine();
                                    System.out.println("Preis");
                                    preis = in.nextFloat();
                                    System.out.println("Anzahl");
                                    anzahl = in.nextInt();
                                    
                                    client.remProdukt(verwalter, pname);
                                    client.addProdukt(verwalter, pname, kategorie, markt, preis, anzahl);
                                    token = true;

                                }
                                
                                
                                if("I".equals(eingabe)|"i".equals(eingabe)){
                                    
                                    produktliste = client.getProduktliste(verwalter);
                                    client.printProduktliste(produktliste);
                                    token = true;

                                }
                                
                                
                                if("A".equals(eingabe)|"a".equals(eingabe)){
                                    
                                    List<String> Liste = client.getKategorieliste(verwalter);
                                    System.out.println(Liste);
                                    token = true;

                                }
                                
                                
                                if("K".equals(eingabe)|"k".equals(eingabe)){
                                    
                                    System.out.println("Bitte geben Sie die Kategorie an deren Produkte Sie sehen wollen.");
                                    eingabe = in.nextLine();
                                    List<Produkt> kategorieliste = client.getKategorieprodukte(verwalter, eingabe);
                                    System.out.println(kategorieliste);
                                    token = true;

                                }
                                
                                
                                if("Back".equals(eingabe)){
                                    t3 = true;
                                    token = true;

                                }
                                
                                
                                if("SaveExit".equals(eingabe)){
                                    
                                    client.save(pfad);
                                    token = true;
                                    t2 = true;
                                    t3 = true;

                                }
                                
                                
                                if("Exit".equals(eingabe)){
                                    
                                    token = true;
                                    t2 = true;
                                    t3 = true;

                                }

                            }
                        }

                    }

                }
            }
            
            in.close(); // wenn hier angekommen dann wurden alle Tokens auf true gesetzt und das Programm soll beendet werden. Der input Stream wird hier geschlossen.
        }
        System.exit(0); //Das Programm hier beendet.
        
    }

}
