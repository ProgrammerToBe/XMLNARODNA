package korenski.soap;

import java.io.File;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import korenski.model.soap.BankaPort;
import korenski.repository.soap.BankaPortRepository;
import korenski.soap.odobrenje.OdobrenjeRequest;
import korenski.soap.odobrenje.OdobrenjeResponse;
import korenski.soap.zaduzenje.ZaduzenjeRequest;
import korenski.soap.zaduzenje.ZaduzenjeResponse;

@Component
public class NarodnaKlijent {
	@Autowired
	private WebServiceTemplate webServiceTemplate;
	 
	@Autowired
	private BankaPortRepository bankaPortRepository;
	
	public String posaljiOdobrenjeClearing(korenski.soap.odobrenjeclearing.OdobrenjeRequest odobrenjeRequest) throws JAXBException {

		System.out.println("Pravim odobrenje Clearing!");
		  
		korenski.soap.odobrenjeclearing.OdobrenjeRequest request = new korenski.soap.odobrenjeclearing.OdobrenjeRequest();
	    
		JAXBContext context = JAXBContext.newInstance("korenski.soap.odobrenjeclearing");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		//OdobrenjeRequest odobrenjereq = (OdobrenjeRequest) unmarshaller.unmarshal(new File("./files/xmls/Odobrenje.xml"));
		
		korenski.soap.odobrenjeclearing.OdobrenjeRequest odobrenjereq = odobrenjeRequest;
		
		String swiftCode = odobrenjereq.getZahtev().getPorukaElement().getBanka().getSwiftKod();
		
		BankaPort bp = new BankaPort();
		bp = bankaPortRepository.findBySwiftCode(swiftCode);
		
		request.setZahtev(odobrenjereq.getZahtev());
		
		System.out.println("Saljem odobrenje Clearing!");
						
		webServiceTemplate.setDefaultUri("http://localhost:" + bp.getPort() + "/ws_poslovne/odobrenjeclearing");
		webServiceTemplate.setMarshaller(jaxb2Marshaller("korenski.soap.odobrenjeclearing"));
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller("korenski.soap.odobrenjeclearing"));
			   
			
			
		korenski.soap.odobrenjeclearing.OdobrenjeResponse odgovor = (korenski.soap.odobrenjeclearing.OdobrenjeResponse) webServiceTemplate.marshalSendAndReceive(request);
		
		System.out.println("Stigao odgovor Clearing!");

		   
		return odgovor.getOdgovor();
	  }
	
	public String posaljiOdobrenjeRtgs(korenski.soap.odobrenjertgs.OdobrenjeRequest odobrenjeRequest) throws JAXBException {

		System.out.println("Pravim odobrenje RTGS!");
		  
		korenski.soap.odobrenjertgs.OdobrenjeRequest request = new korenski.soap.odobrenjertgs.OdobrenjeRequest();
	    
		JAXBContext context = JAXBContext.newInstance("korenski.soap.odobrenjertgs");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		//OdobrenjeRequest odobrenjereq = (OdobrenjeRequest) unmarshaller.unmarshal(new File("./files/xmls/Odobrenje.xml"));
		
		korenski.soap.odobrenjertgs.OdobrenjeRequest odobrenjereq = odobrenjeRequest;
		
		String swiftCode = odobrenjereq.getZahtev().getPorukaElement().getBanka().getSwiftKod();
		
		BankaPort bp = new BankaPort();
		bp = bankaPortRepository.findBySwiftCode(swiftCode);
		
		request.setZahtev(odobrenjereq.getZahtev());
		  
		System.out.println("Saljem odobrenje RTGS!");
						
		webServiceTemplate.setDefaultUri("http://localhost:" + bp.getPort() + "/ws_poslovne/odobrenjertgs");
		webServiceTemplate.setMarshaller(jaxb2Marshaller("korenski.soap.odobrenjertgs"));
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller("korenski.soap.odobrenjertgs"));
			   
			
			
		korenski.soap.odobrenjertgs.OdobrenjeResponse odgovor = (korenski.soap.odobrenjertgs.OdobrenjeResponse) webServiceTemplate.marshalSendAndReceive(request);
		
		System.out.println("Stigao odgovor RTGS!");

		   
		return odgovor.getOdgovor();
	  }

	 
	public String posaljiZaduzenje() throws JAXBException {

		System.out.println("Pravim zaduzenje!");
		  
		ZaduzenjeRequest request = new ZaduzenjeRequest();
	    
		JAXBContext context = JAXBContext.newInstance("korenski.soap.zaduzenje");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ZaduzenjeRequest zaduzenjereq = (ZaduzenjeRequest) unmarshaller.unmarshal(new File("./files/xmls/Zaduzenje.xml"));
		  
		String swiftCode = zaduzenjereq.getZahtev().getBanka().getSwiftKod();
		BankaPort bp = bankaPortRepository.findBySwiftCode(swiftCode);
		
		
		
		request.setZahtev(zaduzenjereq.getZahtev());
			  
		System.out.println("Saljem zaduzenje!");
			
		webServiceTemplate.setDefaultUri("http://localhost:" + bp.getPort() + "/ws_poslovne/zaduzenje");
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
