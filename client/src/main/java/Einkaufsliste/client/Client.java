/*
@author Daniel Meerwald

Diese Klasse stellt alle Methoden zur Verfuegung, die für einen Client benoetigt werden.
Sie macht aus Parametern der Methoden HTTP-Anfragen, die an den Server, 
also das REST Projekt, geschickt werden, das diese weiter an das CORE Projekt leitet.
*/
package Einkaufsliste.client;

import Einkaufsliste.core.Einkaufsliste;
import Einkaufsliste.core.ListenListenhelper;
import Einkaufsliste.core.Listenhelper;
import Einkaufsliste.core.Produkt;
import Einkaufsliste.core.Stringlisthelper;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;

public class Client {
    
    private HttpClient httpClient;// Der verwendete Client.
    private String baseUri;// Die Basis URI zum Webservice.
    private final String xml = "application/xml";// Feld für den Mediatype XML.
    private final String json = "application/json";// Feld für den Mediatype json.
    
    
    
    public Client(String baseUri) { // Im Konstruktor wird die Basis URL, also die IP unseres Servers und der Port, übergeben.
        this.baseUri = baseUri;
        this.httpClient = new HttpClient();
    }
   
    
    
    private Object unmarshallfromXML(Reader in, JAXBContext jaxbContext) throws JAXBException {  // Die Methode stellt aus den erhaltenen XML Dokumenten wieder Java Objekte her, die verwendet werden koennen.
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller(); //P: ERstellt unmarshaller für bestimmtes Objekt?!
        return unmarshaller.unmarshal(in); //P: gibt dann eigentliches Objekt zurück.
    }    
  
    
    
    public String getBaseUri() {
        return baseUri;
    }
    
    
    
    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }
    
    
    
    public void printProduktliste(List<Produkt> pl) { //Rueckgabemethode, damit die Produktliste im Client ausgegeben werden kann.
        System.out.println(pl);
    }

    
    
    public void printListenliste(List<Einkaufsliste> ll) { //Rueckgabemethode, damit die Einkauflistenliste im Client ausgegeben werden kann.
        System.out.println(ll);
    }
   
    
    
    public Einkaufsliste addEinkaufsliste(String Name) throws IOException, JAXBException {

        PostMethod postMethod = new PostMethod(getBaseUri() + "/Einkaufsliste/" + Name);//Postmethode erstellen.
        postMethod.setRequestHeader("Accept", xml);//Medientyp festlegen.
        int responseCode = httpClient.executeMethod(postMethod);//Methode ausfuehren.


        String response = postMethod.getResponseBodyAsString();//Response abfangen.
        JAXBContext jaxbContext = JAXBContext.newInstance(Einkaufsliste.class);//Content anlegen, damit auch ein Objekt zurueck gegeben werden kann.

        return (Einkaufsliste) unmarshallfromXML(new StringReader((response)), jaxbContext);//Zurueckliefern des Einkaufsliste Objekts.
    }
    
    
    
    public void remEinkaufsliste(String EKname) throws IOException {

        DeleteMethod deleteMethod = new DeleteMethod(getBaseUri() + "/Einkaufsliste/" + EKname);//Deletemethode erstellen.
        deleteMethod.setRequestHeader("Accept", xml);
        int responseCode = this.httpClient.executeMethod(deleteMethod);
    }
    
    
    
    public List<Einkaufsliste> getListenListe() throws IOException, JAXBException { 

        GetMethod getMethod = new GetMethod(getBaseUri() + "/Einkaufsliste");
        getMethod.setRequestHeader("Accept", xml);
        int responseCode = this.httpClient.executeMethod(getMethod);

        String response = getMethod.getResponseBodyAsString();

        JAXBContext jaxbContext = JAXBContext.newInstance(ListenListenhelper.class);

        ListenListenhelper Help = (ListenListenhelper)unmarshallfromXML(new StringReader((response)),jaxbContext);
        return Help.getListen();
    }
    
    
    
    public List<Produkt> getProduktliste(String EKname) throws IOException, JAXBException {

        GetMethod getMethod = new GetMethod(getBaseUri() + "/Einkaufsliste/" + EKname);//Getmethode erstellen.
        getMethod.setRequestHeader("Accept", xml);
        int responseCode = this.httpClient.executeMethod(getMethod);

        String response = getMethod.getResponseBodyAsString();

        JAXBContext jaxbContext = JAXBContext.newInstance(Listenhelper.class);

        Listenhelper Help = (Listenhelper) unmarshallfromXML(new StringReader((response)), jaxbContext); //Anlegen eines Listenhelper Objekts damit Annotaionen verfuegbar sind.
        return Help.getProdukte(); //Zurueckliefern des Einkaufsliste Objekts.

    }   
 
    
    
    public List<String> getKategorieliste(String EKname) throws IOException, JAXBException{
        
        GetMethod getMethod = new GetMethod(getBaseUri() + "/Einkaufsliste/" + EKname + "/Kliste");//Getmethode erstellen.
        getMethod.setRequestHeader("Accept", xml);
        int responseCode = this.httpClient.executeMethod(getMethod);

        String response = getMethod.getResponseBodyAsString();

        JAXBContext jaxbContext = JAXBContext.newInstance(Stringlisthelper.class);

        Stringlisthelper Help = (Stringlisthelper) unmarshallfromXML(new StringReader((response)), jaxbContext); //Anlegen eines Stringhelpers Objekts damit Annotaionen verfuegbar sind.
        return Help.getKategorieen();  
    }

    
    
    public List<Produkt> getKategorieprodukte(String EKname, String katname) throws IOException, JAXBException{
        
        GetMethod getMethod = new GetMethod(getBaseUri() + "/Einkaufsliste/" + EKname + "/" + katname +"/Kliste");//Getmethode erstellen.
        getMethod.setRequestHeader("Accept", xml);
        int responseCode = this.httpClient.executeMethod(getMethod);

        String response = getMethod.getResponseBodyAsString();

        JAXBContext jaxbContext = JAXBContext.newInstance(Listenhelper.class);

        Listenhelper Help = (Listenhelper) unmarshallfromXML(new StringReader((response)), jaxbContext); //Anlegen eines Listenhelper Objekts damit Annotaionen verfuegbar sind.
        return Help.getProdukte(); //Zurueckliefern des Einkaufsliste Objekts.
    }



    public void addProdukt(String EKname, String Pname, String Kategorie, String Markt, float Preis, int Anzahl) throws IOException, JAXBException {

        PutMethod putMethod = new PutMethod(getBaseUri() + "/Einkaufsliste/" + EKname + "/" + Pname);//Hinzufuegen der Pathparameter.
        
        NameValuePair[] qp = new NameValuePair[]{new NameValuePair("kategorie", Kategorie), //Hinzufuegen der Query Parameter.
          new NameValuePair("markt", Markt), new NameValuePair("preis", "" + Preis), new NameValuePair("anzahl", "" + Anzahl)};
        
        putMethod.setQueryString(qp);

        int responseCode = this.httpClient.executeMethod(putMethod);

        String response = putMethod.getResponseBodyAsString();
        JAXBContext jaxbContext = JAXBContext.newInstance(Produkt.class);
    }


    
    public void remProdukt(String EKname, String Pname) throws IOException {

        DeleteMethod deleteMethod = new DeleteMethod(getBaseUri() + "/Einkaufsliste/" + EKname + "/" + Pname);
        deleteMethod.setRequestHeader("Accept", xml);
        int responseCode = this.httpClient.executeMethod(deleteMethod);
    }
    

    
    public int open(String pfad) throws IOException, JAXBException{
        PutMethod putMethod = new PutMethod(getBaseUri() + "/Einkaufsliste/open");
        NameValuePair[] qp = new NameValuePair[]{new NameValuePair("pfad", pfad)};
        putMethod.setQueryString(qp);
        
        int responseCode = this.httpClient.executeMethod(putMethod);

        String response = putMethod.getResponseBodyAsString();
        JAXBContext jaxbContext = JAXBContext.newInstance(Produkt.class);

        return responseCode;
    }
        

    
    public int save(String pfad) throws IOException, JAXBException{
        PutMethod putMethod = new PutMethod(getBaseUri() + "/Einkaufsliste/save");
        NameValuePair[] qp = new NameValuePair[]{new NameValuePair("pfad", pfad)};
        putMethod.setQueryString(qp);
        
        int responseCode = this.httpClient.executeMethod(putMethod);

        String response = putMethod.getResponseBodyAsString();
        JAXBContext jaxbContext = JAXBContext.newInstance(Produkt.class);
        return responseCode;
    }
}
