/*
@author Daniel Meerwald

Diese Klasse stellt die Implementierung der Methoden des EinkaufslisteResource- Interface dar. 
Sie bedient sich größtenteils der Methoden aus der EinkaufslisteService- Klasse.
*/

package Einkaufsliste.rest;

import Einkaufsliste.core.Einkaufsliste;
import Einkaufsliste.core.EinkaufslisteService;
import Einkaufsliste.core.ListenListenhelper;
import Einkaufsliste.core.Listenhelper;
import Einkaufsliste.core.Produkt;
import Einkaufsliste.core.Stringlisthelper;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class EinkaufslisteResourceImpl implements EinkaufslisteResource {
    
    @Context
    private EinkaufslisteService einkauflisteService;
    
    
    @Override
    public Response createEinkaufsliste(UriInfo uriInfo, String Name) {

        if (Name == null || Name.isEmpty()) { //Bei unzulässiger Anfrage wird der HTTP Statuscode auf BAD_REQUEST=400 gesetzt.
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Einkaufsliste newListe = getEinkaufslisteService().addEinkaufsliste(Name); //Anlegen einer neuen Einkaufsliste, hier werden Methoden aus dem Service verwendet.
        return Response.status(Response.Status.CREATED).entity(newListe).build(); //Bei Erfolg wird der HTTP Statuscode auf CREATED=201 gesetzt.
    }
    
    public EinkaufslisteService getEinkaufslisteService() {
        return einkauflisteService;
    }
    
    
    
    @Override
    public Response getProduktliste(String name) {

        Einkaufsliste EKListe = einkauflisteService.getEinkaufsliste(name);
        if (EKListe == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); //Bei unzulässiger Abfrage wird der HTTP Statuscode auf NOT_FOUND=404 gesetzt.
        }
        return Response.ok(new Listenhelper(EKListe.getEinkaufliste())).build(); //Hier kommt unter anderem der Listenhelper zum Einsatz.
    }
    
    
    
    @Override
    public Response remEinkaufsliste(String name) {

        Einkaufsliste EKListe = einkauflisteService.getEinkaufsliste(name);
        if (EKListe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        einkauflisteService.remEinkaufsliste(name);
       
        return Response.status(Response.Status.OK).build(); //Wenn alles geklappt hat, wird der HTTP Statuscode auf OK=200 gesetzt.
    }

    
    
    @Override
    public Response addProduktToEinkaufsliste(UriInfo uriInfo, String Listenname, String Name, String Kategorie, String Markt, float Preis, int Anzahl) {

        Einkaufsliste EKListe = einkauflisteService.getEinkaufsliste(Listenname);
        if (EKListe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        EKListe.addProdukt(Name, Kategorie, Markt, Preis, Anzahl);
        return Response.status(Response.Status.CREATED).build(); //Bei Erfolg wird der HTTP Statuscode auf CREATED=201 gesetzt.
    }
    
   
    
    @Override
    public Response getProdukt(String Listenname, String Name) {

        Einkaufsliste EKListe = einkauflisteService.getEinkaufsliste(Listenname);
        if (EKListe == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); 
        }
        Produkt P = EKListe.getProdukt(Name);
        
        return Response.ok(P).build();
    }
    
    
    
    @Override
    public Response remProdukt(String EKname, String Pname) {

        Einkaufsliste EKListe = einkauflisteService.getEinkaufsliste(EKname);
        if (EKListe == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); 
        }
        EKListe.removeProdukt(Pname);
        return Response.status(Response.Status.OK).build(); 
    }
    
   
    
    @Override
    public Response getListenliste() {
        
        List<Einkaufsliste> Gesamtliste =  einkauflisteService.getListenliste();
        ListenListenhelper Gesamtlistehelper = new ListenListenhelper(Gesamtliste);
     
        return Response.ok(Gesamtlistehelper).build();
    }
    

    
    @Override
    public Response save(String pfad){
        
        einkauflisteService.save(pfad);
        
        return Response.status(Response.Status.OK).build(); 
    }
    

    
    @Override
    public Response open(String pfad){
        
        einkauflisteService.open(pfad);
        
        return Response.status(Response.Status.OK).build(); 
    }

 
    
    @Override
    public Response getKategorieliste(String Listenname) {
        
         List<String> EKListe = einkauflisteService.getEinkaufsliste(Listenname).showallKategorie();
        if (EKListe == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); //Bei unzulässiger Abfrage wird der HTTP Statuscode auf NOT_FOUND=404 gesetzt.
        }
        return Response.ok(new Stringlisthelper(EKListe)).build(); //Hier kommt unter anderem der Listenhelper zum Einsatz.
    }



    @Override
    public Response getKategorieprodukte(String Listenname, String name) {
        
        List<Produkt> EKListe = einkauflisteService.getEinkaufsliste(Listenname).getKategorieprodukte(name);
        if (EKListe == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); //Bei unzulässiger Abfrage wird der HTTP Statuscode auf NOT_FOUND=404 gesetzt.
        }
        return Response.ok(new Listenhelper(EKListe)).build(); //Hier kommt unter anderem der Listenhelper zum Einsatz.
    }
}
