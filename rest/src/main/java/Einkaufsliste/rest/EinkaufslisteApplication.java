/*
@author Daniel Meerwald

Diese Klasse macht die EinkaufslisteResourceImpl Klasse mit der EinkaufslisteService Klasse bekannt, 
damit die EinkaufslisteResourceImpl Klasse auch genutzt werden kann.
*/

package Einkaufsliste.rest;

import Einkaufsliste.core.EinkaufslisteService;
import Einkaufsliste.core.EinkaufslisteServiceImpl;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class EinkaufslisteApplication extends ResourceConfig{

    public EinkaufslisteApplication(){
        
        register(EinkaufslisteResourceImpl.class); //Registrieren der EinkaufslisteResourceImpl, um Klasse nutzbar zu machen.
        
        register(new AbstractBinder(){

                @Override
                protected void configure(){
                    
                    bind(new EinkaufslisteServiceImpl()).to(EinkaufslisteService.class); //Binding des Interfaces mit seiner Implementierung.                    
                }
            }
        );
    }
}
