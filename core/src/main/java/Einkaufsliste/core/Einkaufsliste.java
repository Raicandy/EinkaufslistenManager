/*
@author Daniel Meerwald

Diese Klasse stellt das Einkaufsliste- Objekt mit allen nötigen Methoden und Annotationen zur Verfügung.
*/

package Einkaufsliste.core;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Einkaufliste")
public class Einkaufsliste {
    
    private List<Produkt> einkaufsliste = new ArrayList<>();
    private String name;
    
    public Einkaufsliste() {}    
    


    public Einkaufsliste(String Name){
        
        this.name=Name;
    }
    


    public String toString(){
        
        return name + " mit " + einkaufsliste.size() + " Produkten." + " Gesamtbetrag: "+ Gesamtpreis() + " Euro";
    }
    
    @XmlElement(name = "LISTE")
    @XmlElementWrapper(name = "wrapper")
    public List<Produkt> getEinkaufliste() {
        return einkaufsliste;
    }
    


    @XmlElement(name = "Name")
    public String getName(){
        return name;
    }
    


    public void setName(String Name){
        this.name=Name;
    }



    public void addProdukt(String Name, String Kategorie, String Markt, float Preis, int Anzahl){ 
        
        Produkt p = new Produkt(Name, Kategorie, Markt, Preis, Anzahl);
                
        einkaufsliste.add(p);
    }
    


    public Produkt getProdukt(String Name){
        
        int i=0;
        boolean indikator = false;
        
        while (i < einkaufsliste.size() && indikator == false){ //Es wird bis zum Ende der Liste gesucht.
            
            if(einkaufsliste.get(i).getName().equals(Name)){ //Wenn das Objekt gefunden wurde, wird der Indikator auf true gesetzt.
                indikator = true;
            }
            else{
                i++; //Andernfalls wird das naechste Objekt verglichen.
            }
        }
        
        if(indikator==false){
            return null;   //Erzeugt einen Fehlercode, falls eine unzulaessige Anfrage gestellt wurde.
        }
        else{
            return einkaufsliste.get(i);
        }
    }
    


    public void removeProdukt(String Name){
        
        int i=0;
        boolean indikator = false;
        
        while (i <= einkaufsliste.size() && indikator == false){
            
            if(einkaufsliste.get(i).getName().equals(Name)){ //Wenn das Objekt gefunden wurde, wird es geloescht.
                einkaufsliste.remove(i); 
                indikator=true; //Ende der Methode, wenn das Objekt gefunden wurde.
            }
            else{
                i++; 
            }
        }
    }
    


    public void clear(){ 
        
        einkaufsliste.clear();
    }
    


    public float Gesamtpreis(){
        
        float Gesamtpreis = 0;
        
        for(int i=0; i < einkaufsliste.size(); i++){ //Simple Addition des Gesamtpreises.
            
            Gesamtpreis = Gesamtpreis + (einkaufsliste.get(i).getPreis() * einkaufsliste.get(i).getAnzahl());
        }
        return Gesamtpreis;
    }
    


    public List<String> showallKategorie(){ 
        //Die Methode stellt eine Liste aller Kategorien zur Verfügung, wobei Doppelungen entfernt werden.
        List<String> Kategorien = new ArrayList<>();
        int i=0;
        
        while( i < einkaufsliste.size()){ 
           
            if(Kategorien.contains(einkaufsliste.get(i).getKategorie())){ //Wenn die Kategorie bereits in der neuen Liste vorhanden ist, wird weiter verglichen.
                i++;
            }
            else{
            
                Kategorien.add(einkaufsliste.get(i).getKategorie()); //Andernfalls wird die Kategorie in die Liste gespeichert und ausgegeben.
                System.out.println(einkaufsliste.get(i).getKategorie());
            }
        }
        return Kategorien;
    }


    
    public List<Produkt> getKategorieprodukte(String kategorie){
        
        List<Produkt> Kategorien = new ArrayList<>();
        int i=0;
        
        while( i < einkaufsliste.size()){ 
           
            if(kategorie.equals(einkaufsliste.get(i).getKategorie())){ //Wenn die Kategorie bereits in der neuen Liste vorhanden ist, wird weiter verglichen.

                Kategorien.add(einkaufsliste.get(i));
                i++;
            }
            else{
                i++;
            }
        }
        return Kategorien;
    }
}
