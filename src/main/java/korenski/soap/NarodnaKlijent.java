package korenski.soap;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

//import korenski.model.dto.BankaPort;
//import korenski.repository.dtos.BankaPortRepository;
import korenski.soap.clearing.ClearingRequest;
import korenski.soap.clearing.ClearingResponse;
import korenski.soap.odobrenje.OdobrenjeRequest;
import korenski.soap.odobrenje.OdobrenjeResponse;
import korenski.soap.rtgs.RtgsRequest;
import korenski.soap.rtgs.RtgsResponse;
import korenski.soap.zaduzenje.ZaduzenjeRequest;
import korenski.soap.zaduzenje.ZaduzenjeResponse;

@Component
public class NarodnaKlijent {
	@Autowired
	private WebServiceTemplate webServiceTemplate;
	 
	public String posaljiOdobrenje() throws JAXBException {

		System.out.println("Pravim odobrenje!");
		  
		OdobrenjeRequest request = new OdobrenjeRequest();
	    
		JAXBContext context = JAXBContext.newInstance("korenski.soap.odobrenje");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		OdobrenjeRequest odobrenjereq = (OdobrenjeRequest) unmarshaller.unmarshal(new File("./files/xmls/Odobrenje.xml"));
		  
		
		request.setZahtev(odobrenjereq.getZahtev());
		  
		System.out.println("Saljem odobrenje!");
						
		webServiceTemplate.setDefaultUri("http://localhost:8080/ws_poslovne/odobrenje");
		webServiceTemplate.setMarshaller(jaxb2Marshaller("korenski.soap.odobrenje"));
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller("korenski.soap.odobrenje"));
			   
			
			
		OdobrenjeResponse odgovor = (OdobrenjeResponse) webServiceTemplate.marshalSendAndReceive(request);
		
		System.out.println("Stigao odgovor!");

		   
		return odgovor.getOdgovor();
	  }
	 
	public String posaljiZaduzenje() throws JAXBException {

		System.out.println("Pravim zaduzenje!");
		  
		ZaduzenjeRequest request = new ZaduzenjeRequest();
	    
		JAXBContext context = JAXBContext.newInstance("korenski.soap.zaduzenje");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ZaduzenjeRequest zaduzenjereq = (ZaduzenjeRequest) unmarshaller.unmarshal(new File("./files/xmls/Zaduzenje.xml"));
		  
		
		request.setZahtev(zaduzenjereq.getZahtev());
			  
		System.out.println("Saljem zaduzenje!");
			
		webServiceTemplate.setDefaultUri("http://localhost:8080/ws_poslovne/zaduzenje");
		webServiceTemplate.setMarshaller(jaxb2Marshaller("korenski.soap.zaduzenje"));
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller("korenski.soap.zaduzenje"));
			   
		ZaduzenjeResponse odgovor = (ZaduzenjeResponse) webServiceTemplate.marshalSendAndReceive(request);
		
		System.out.println("Stigao odgovor!");
		   
		return odgovor.getOdgovor();
	}

	 
	private Jaxb2Marshaller jaxb2Marshaller(String path) {

		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath(path);
	    return jaxb2Marshaller;
	}
}
