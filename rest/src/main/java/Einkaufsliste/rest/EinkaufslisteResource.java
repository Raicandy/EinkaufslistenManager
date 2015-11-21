/*
@author Daniel Meerwald

Das Interface deklariert alle Methoden und stellt alle Annotationen für die HTTP Methoden zur Verfügung, 
die vom Client genutzt werden, um mit dem Rest und somit dem Core Projekt zu kommunizieren.
 */

package Einkaufsliste.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("Einkaufsliste")
public interface EinkaufslisteResource {
    
    @POST
    @Path("{EKname}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response createEinkaufsliste(@Context UriInfo uriInfo, @PathParam("EKname") String Name);
    
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response getListenliste();
    
    
    @PUT
    @Path("save")
    Response save(@QueryParam("pfad") String pfad);
    
    
    @PUT
    @Path("open")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response open(@QueryParam("pfad") String pfad);
    
    
    @GET
    @Path("{EKname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response getProduktliste(@PathParam("EKname") String name);
    
    
    @GET
    @Path("{EKname}/Kliste")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response getKategorieliste(@PathParam("EKname") String name);
    
    
    @GET
    @Path("{EKname}/{Kname}/Kliste")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response getKategorieprodukte(@PathParam("EKname") String name, @PathParam("Kname") String Kategorie);
    
    
    @DELETE
    @Path("{EKname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response remEinkaufsliste(@PathParam("EKname") String name);
    
    
    @PUT
    @Path("{EKname}/{Pname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response addProduktToEinkaufsliste(
            @Context UriInfo uriInfo,
            @PathParam("EKname") String Listenname,
            @PathParam("Pname") String Name,
            @QueryParam("kategorie") String Kategorie,
            @QueryParam("markt") String Markt,
            @QueryParam("preis") @DefaultValue("0") float Preis,
            @QueryParam("anzahl") @DefaultValue("1")int Anzahl);
    
    
    @GET
    @Path("{EKname}/{Pname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response getProdukt(@PathParam("EKname") String Listenname,
                        @PathParam("pname") String Name);
    
    
    @DELETE
    @Path("{EKname}/{Pname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response remProdukt(@PathParam("EKname") String EKname,
                         @PathParam("Pname") String Pname);
    
}
