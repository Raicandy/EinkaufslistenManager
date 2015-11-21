/*
@author Daniel Meerwald

Das Interface deklariert alle Methoden für den Service des Einkaufslistenmanagers. 
Der Service verwaltet die Einkaufslisten.

*/
package Einkaufsliste.core;

import java.io.File;
import java.util.List;

public interface EinkaufslisteService {
    
    Einkaufsliste addEinkaufsliste(String Name);
   
    public void remEinkaufsliste(String Name);

    Einkaufsliste getEinkaufsliste(String Name);
   
    List<Einkaufsliste> getListenliste();
    
    public void  open(String pfad);
    
    public void save(String pfad);
    
}
