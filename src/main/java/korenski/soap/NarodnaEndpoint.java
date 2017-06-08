package korenski.soap;

import javax.jws.HandlerChain;
import javax.jws.soap.SOAPBinding;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import korenski.soap.clearing.Clearing;
import korenski.soap.clearing.ClearingRequest;
import korenski.soap.clearing.ClearingResponse;
import korenski.soap.izvestaji_model.IzvestajRequest;
import korenski.soap.izvestaji_model.IzvestajResponse;
import korenski.soap.nalozi_model.NalogRequest;
import korenski.soap.nalozi_model.NalogResponse;
import korenski.soap.odobrenje.OdobrenjeRequest;
import korenski.soap.odobrenje.OdobrenjeResponse;
import korenski.soap.rtgs.RtgsRequest;
import korenski.soap.rtgs.RtgsResponse;
import korenski.soap.zaduzenje.ZaduzenjeRequest;
import korenski.soap.zaduzenje.ZaduzenjeResponse;

@Endpoint
@HandlerChain(file = "handlers.xml")

public class NarodnaEndpoint {
	private static final String NAMESPACE_URI_NALOG = "http://korenski/soap/nalozi_model";
	private static final String NAMESPACE_URI_IZVESTAJ = "http://korenski/soap/izvestaji_model";
	//obavezno brisi
	private static final String NAMESPACE_URI_RTGS = "http://korenski/soap/rtgs";
	private static final String NAMESPACE_URI_CLEARING = "http://korenski/soap/clearing";
	
	@PayloadRoot(namespace = NAMESPACE_URI_NALOG, localPart = "nalogRequest")
	@ResponsePayload
	public NalogResponse getNalog(@RequestPayload NalogRequest request) {
		NalogResponse response = new NalogResponse();
		
		System.out.println("Pogodjena metoda sendNalogRequest");
		System.out.println("Primljen nalog sa svrhom placanja "+ request.getNalog().getSvrhaPlacanja());
		response.setOdgovor("Primljen nalog sa svrhom placanja "+ request.getNalog().getSvrhaPlacanja());

		return response;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI_IZVESTAJ, localPart = "izvestajRequest")
	@ResponsePayload
	public IzvestajResponse getIzvestaj(@RequestPayload IzvestajRequest request) {
		IzvestajResponse response = new IzvestajResponse();
		
		System.out.println("Pogodjena metoda izvestajRequest");
		System.out.println("Primljen zahtev za izvestaj "+ request.getZahtev());
		response.setOdgovor("Primljen zahtev za izvestaj "+ request.getZahtev());

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI_RTGS, localPart = "rtgsRequest")
	@ResponsePayload
	public RtgsResponse getRtgs(@RequestPayload RtgsRequest request) {
		RtgsResponse response = new RtgsResponse();
		
		System.out.println("Pogodjena metoda rtgsRequest");
		System.out.println("Primljen zahtev za rtgs "+ request.getZahtev().getBankaDuznik().getNaziv());
		response.setOdgovor("Primljen zahtev za rtgs "+ request.getZahtev().getBankaDuznik().getNaziv());

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI_CLEARING, localPart = "clearingRequest")
	@ResponsePayload
	public ClearingResponse getClearing(@RequestPayload ClearingRequest request) {
		ClearingResponse response = new ClearingResponse();
		
		System.out.println("Pogodjena metoda clearingRequest");
		System.out.println("Primljen zahtev za clearing "+ request.getZahtev().getDatum());
		response.setOdgovor("Primljen zahtev za clearing "+ request.getZahtev().getDatum());

		return response;
	}
}