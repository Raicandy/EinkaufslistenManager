/*
@author Daniel Meerwald

Die Klasse stellt das Produkt- Objekt mit allen nötigen Methoden und Annotationen zur Verfügung.
*/

package Einkaufsliste.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Produkt")
public class Produkt {
    
    private String name;
    private String Kategorie;
    private float Preis;
    private String Markt;
    int Anzahl;
    


    public Produkt() {}
    


    public Produkt(String Name, String Kategorie, String Markt, float Preis, int Anzahl) {

        this.name = Name;
        this.Kategorie = Kategorie;
        this.Markt = Markt;
        this.Anzahl = Anzahl;
        this.Preis = Preis;
    }

    
    
    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }
    
   
    
    @Override
    public String toString() {

        return this.name + " " + this.Anzahl + "x bei " + this.Markt + " fuer: " + this.Preis + " Euro," + " Kat: " + this.Kategorie;
    }
    
    
    
    public void setName(String name) {
        this.name = name;
    }

    
    
    @XmlElement(name = "Kategorie")
    public String getKategorie() {
        return Kategorie;
    }

    
    
    public void setKategorie(String kategorie) {
        this.Kategorie = kategorie;
    }
    
   
    
    @XmlElement(name = "Anzahl")
    public int getAnzahl() {
        return Anzahl;
    }

    
    
    public void setAnzahl(int Anzahl) {
        this.Anzahl = Anzahl;
    }
    
    
    
    @XmlElement(name = "Markt")
    public String getMarkt() {
        return Markt;
    }
    
    
    
    public void setMarkt(String Markt) {
        this.Markt = Markt;
    }
    
    
    
    @XmlElement(name = "Preis")
    public float getPreis() {
        return Preis;
    }

    
    
    public void setPreis(float Preis) {
        this.Preis = Preis;
    }
}
