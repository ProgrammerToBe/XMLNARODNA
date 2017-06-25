package korenski.soap;

import java.math.BigDecimal;

import javax.jws.HandlerChain;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import korenski.model.soap.BankaPort;
import korenski.repository.soap.BankaPortRepository;
import korenski.repository.soap.ClearingOdgovorRepository;
import korenski.repository.soap.ClearingRepository;
import korenski.repository.soap.RtgsOdgovorRepository;
import korenski.repository.soap.RtgsRepository;
import korenski.soap.clearing.ClearingRequest;
import korenski.soap.clearing.ClearingResponse;
import korenski.soap.izvestaji_model.IzvestajRequest;
import korenski.soap.izvestaji_model.IzvestajResponse;
import korenski.soap.nalozi_model.NalogRequest;
import korenski.soap.nalozi_model.NalogResponse;
import korenski.soap.pomocni.ClearingHelper;
import korenski.soap.pomocni.RtgsHelper;
import korenski.soap.rtgs.RtgsRequest;
import korenski.soap.rtgs.RtgsResponse;
import korenski.soap.rtgs.TBanka;

@Endpoint
@HandlerChain(file = "handlers.xml")

public class NarodnaEndpoint {
	@Autowired
	private NarodnaKlijent narodnaKlijent;
	
	@Autowired
	private BankaPortRepository bankaPortRepository;
	
	@Autowired
	private RtgsRepository rtgsRepository;
	
	@Autowired
	private RtgsOdgovorRepository rtgsOdgovorRepository;
	
	@Autowired
	private ClearingRepository clearingRepository;
	
	@Autowired
	private ClearingOdgovorRepository clearingOdgovorRepository;
	
	private ClearingHelper clearingHelper = new ClearingHelper();
	
	private RtgsHelper rtgsHelper = new RtgsHelper();

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
		
		System.out.println("Pogodjena metoda rtgsRequest");
		System.out.println("Primljen zahtev za rtgs "+ request.getZahtev().getBankaDuznik().getNaziv());
		
		try {
			narodnaKlijent.posaljiOdobrenjeRtgs(rtgsHelper.makeRequestOdobrenjeRtgs(request));
			
			BigDecimal iznos = request.getZahtev().getIznos();
			TBanka bankaDuznik  = request.getZahtev().getBankaDuznik();
			TBanka bankaPoverilac = request.getZahtev().getBankaPoverilac();
			
			BankaPort duznik = bankaPortRepository.findBySwiftCode(bankaDuznik.getSwiftKod());
			BankaPort poverilac = bankaPortRepository.findBySwiftCode(bankaPoverilac.getSwiftKod());
			
			duznik.setStanje(duznik.getStanje()-iznos.doubleValue());
			poverilac.setStanje(poverilac.getStanje()+iznos.doubleValue());
			
			try {
				bankaPortRepository.save(duznik);
				bankaPortRepository.save(poverilac);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			System.out.println("Nesto je poslo po zlu metoda: getRTGS");
			e.printStackTrace();
		}
		
		RtgsResponse response = rtgsHelper.makeResponseRtgsZaduzenje(request);
		
		rtgsRepository.save(request.getZahtev());
//		response.getOdgovor().getBanka().setId(null);
//		rtgsOdgovorRepository.save(response.getOdgovor());
//		
		request.getZahtev().getBankaDuznik().setId(null);
		request.getZahtev().getBankaPoverilac().setId(null);
		
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI_CLEARING, localPart = "clearingRequest")
	@ResponsePayload
	public ClearingResponse getClearing(@RequestPayload ClearingRequest request) {
		
		System.out.println("Pogodjena metoda clearingRequest");
		System.out.println("Primljen zahtev za clearing "+ request.getZahtev().getDatum());
		
		try {
			narodnaKlijent.posaljiOdobrenjeClearing(clearingHelper.makeRequestOdobrenjeClearing(request));
			
			BigDecimal iznos = request.getZahtev().getIznos();
			korenski.soap.clearing.TBanka bankaDuznik  = request.getZahtev().getBankaDuznik();
			korenski.soap.clearing.TBanka bankaPoverilac = request.getZahtev().getBankaPoverilac();
			
			BankaPort duznik = bankaPortRepository.findBySwiftCode(bankaDuznik.getSwiftKod());
			BankaPort poverilac = bankaPortRepository.findBySwiftCode(bankaPoverilac.getSwiftKod());
			
			duznik.setStanje(duznik.getStanje()-iznos.doubleValue());
			poverilac.setStanje(poverilac.getStanje()+iznos.doubleValue());
			
			try {
				bankaPortRepository.save(duznik);
				bankaPortRepository.save(poverilac);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			System.out.println("Nesto je poslo po zlu metoda: getClearing");
			e.printStackTrace();
		}
		
		ClearingResponse response = clearingHelper.makeResponseClearingZaduzenje(request);
		
		clearingRepository.save(request.getZahtev());
		//clearingOdgovorRepository.save(response.getOdgovor());
		
		/*
		request.getZahtev().getBankaDuznik().setId(null);
		request.getZahtev().getBankaPoverilac().setId(null);
		response.getOdgovor().getBanka().setId(null);
		*/
		return response;
	}
	
	//deo za kliring
//	public ClearingResponse makeResponseClearingZaduzenje(ClearingRequest request){
//		ClearingResponse response = new ClearingResponse();
//		
//		Poruka p = new Poruka();
//		p.setBanka(request.getZahtev().getBankaDuznik());
//		//p.setDatumValute();
//		//p.setIdPoruke("");
//		//p.setIdPorukeNaloga(value);
//		p.setIznos(request.getZahtev().getIznos());
//		//p.setSifraVlute("DIN");
//		response.setOdgovor(p);
//		
//		return response;
//	}
//	
//	public korenski.soap.odobrenjeclearing.OdobrenjeRequest makeRequestOdobrenjeClearing(ClearingRequest request){
//		
//		korenski.soap.odobrenjeclearing.OdobrenjeClearing oc = new korenski.soap.odobrenjeclearing.OdobrenjeClearing();
//		
//		oc.setClearingElement(repackClearing(request));
//		oc.setPorukaElement(makeRequestClearingPoruka(request));
//		
//		korenski.soap.odobrenjeclearing.OdobrenjeRequest or = new korenski.soap.odobrenjeclearing.OdobrenjeRequest();
//		or.setZahtev(oc);
//		
//		return or;
//	}
//	
//	public Clearing repackClearing(ClearingRequest request){
//		TBanka bPoverilac = new TBanka();
//		bPoverilac.setNaziv(request.getZahtev().getBankaPoverilac().getNaziv());
//		bPoverilac.setObracunskiRacun(request.getZahtev().getBankaPoverilac().getObracunskiRacun());
//		bPoverilac.setSwiftKod(request.getZahtev().getBankaPoverilac().getSwiftKod());
//		
//		TBanka bDuznik = new TBanka();
//		bDuznik.setNaziv(request.getZahtev().getBankaDuznik().getNaziv());
//		bDuznik.setObracunskiRacun(request.getZahtev().getBankaDuznik().getObracunskiRacun());
//		bDuznik.setSwiftKod(request.getZahtev().getBankaDuznik().getSwiftKod());
//		
//		StavkePrenosa stavkePrenosa = new StavkePrenosa();
//		for(int i=0; i < request.getZahtev().getStavkePrenosa().getStavkaPrenosa().size(); i++){
//			StavkaPrenosa sp = new StavkaPrenosa();
//			sp.setDuznikNalogodavac(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getDuznikNalogodavac());
//			sp.setPrimalacPoverilac(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPrimalacPoverilac());
//			sp.setIDPoruke(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getIDPoruke());
//			sp.setSvrhaPlacanja(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getSvrhaPlacanja());
//			
//			TFinansijskiPodaci podaciDuznik = new TFinansijskiPodaci();
//			podaciDuznik.setBrojRacuna(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciDuznik().getBrojRacuna());
//			podaciDuznik.setModel(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciDuznik().getModel());
//			podaciDuznik.setPozivNaBroj(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciDuznik().getPozivNaBroj());
//			
//			TFinansijskiPodaci podaciPoverilac = new TFinansijskiPodaci();
//			podaciPoverilac.setBrojRacuna(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getBrojRacuna());
//			podaciPoverilac.setModel(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getModel());
//			podaciPoverilac.setPozivNaBroj(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getPozivNaBroj());
//			
//			PodaciOPlacanju pop = new PodaciOPlacanju();
//			pop.setDatumNaloga(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getDatumNaloga());
//			pop.setDatumValute(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getDatumValute());
//			pop.setOznakaValute(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getOznakaValute());
//			pop.setIznos(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getIznos());
//			pop.setFinansijskiPodaciDuznik(podaciDuznik);
//			pop.setFinansijskiPodaciPoverilac(podaciPoverilac);
//			
//			sp.setPodaciOPlacanju(pop);
//			sp.setDuznikNalogodavac(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getDuznikNalogodavac());
//			sp.setPrimalacPoverilac(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPrimalacPoverilac());
//			sp.setIDPoruke(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getIDPoruke());
//			sp.setSvrhaPlacanja(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getSvrhaPlacanja());
//			
//			stavkePrenosa.getStavkaPrenosa().add(sp);
//		}
//		
//		korenski.soap.odobrenjeclearing.Clearing c = new korenski.soap.odobrenjeclearing.Clearing();
//		c.setBankaPoverilac(bPoverilac);
//		c.setBankaDuznik(bDuznik);
//		c.setDatum(request.getZahtev().getDatum());
//		c.setIznos(request.getZahtev().getIznos());
//		c.setSifraPrometa(request.getZahtev().getSifraPrometa());
//		c.setStavkePrenosa(stavkePrenosa);
//		
//		return c;
//	}
//	
//	public korenski.soap.odobrenjeclearing.Poruka makeRequestClearingPoruka(ClearingRequest request){
//		
//		korenski.soap.odobrenjeclearing.Poruka p = new korenski.soap.odobrenjeclearing.Poruka();
//		
//		TBanka b = new TBanka();
//		b.setNaziv(request.getZahtev().getBankaPoverilac().getNaziv());
//		b.setObracunskiRacun(request.getZahtev().getBankaPoverilac().getObracunskiRacun());
//		b.setSwiftKod(request.getZahtev().getBankaPoverilac().getSwiftKod());
//		
//		p.setBanka(b);
//		//p.setDatumValute();
//		//p.setIdPoruke("");
//		//p.setIdPorukeNaloga(value);
//		p.setIznos(request.getZahtev().getIznos());
//		//p.setSifraVlute("DIN");
//		
//		return p;
//	}
	//deo za kliring kraj
	
	//deo za rtgs
//	public RtgsResponse makeResponseRtgsZaduzenje(RtgsRequest request){
//		RtgsResponse response = new RtgsResponse();
//		
//		korenski.soap.rtgs.Poruka p = new korenski.soap.rtgs.Poruka();
//		
//		p.setBanka(request.getZahtev().getBankaDuznik());
//		//p.setDatumValute();
//		//p.setIdPoruke("");
//		//p.setIdPorukeNaloga(value);
//		p.setIznos(request.getZahtev().getIznos());
//		//p.setSifraVlute("DIN");
//		response.setOdgovor(p);
//		
//		return response;
//	}
//	
//	public korenski.soap.odobrenjertgs.OdobrenjeRequest makeRequestOdobrenjeRtgs(RtgsRequest request){
//		
//		OdobrenjeRtgs or = new OdobrenjeRtgs();
//		
//		or.setRtgsElement(repackRtgs(request));
//		or.setPorukaElement(makeRequestRtgsPoruka(request));
//		
//		korenski.soap.odobrenjertgs.OdobrenjeRequest oreq = new korenski.soap.odobrenjertgs.OdobrenjeRequest();
//		oreq.setZahtev(or);
//		
//		return oreq;
//	}
//	
//	public RTGS repackRtgs(RtgsRequest request){
//		korenski.soap.odobrenjertgs.TBanka bPoverilac = new korenski.soap.odobrenjertgs.TBanka();
//		bPoverilac.setNaziv(request.getZahtev().getBankaPoverilac().getNaziv());
//		bPoverilac.setObracunskiRacun(request.getZahtev().getBankaPoverilac().getObracunskiRacun());
//		bPoverilac.setSwiftKod(request.getZahtev().getBankaPoverilac().getSwiftKod());
//		
//		korenski.soap.odobrenjertgs.TBanka bDuznik = new korenski.soap.odobrenjertgs.TBanka();
//		bDuznik.setNaziv(request.getZahtev().getBankaDuznik().getNaziv());
//		bDuznik.setObracunskiRacun(request.getZahtev().getBankaDuznik().getObracunskiRacun());
//		bDuznik.setSwiftKod(request.getZahtev().getBankaDuznik().getSwiftKod());
//		
//		korenski.soap.odobrenjertgs.TFinansijskiPodaci podaciDuznik = new korenski.soap.odobrenjertgs.TFinansijskiPodaci();
//		podaciDuznik.setBrojRacuna(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciDuznik().getBrojRacuna());
//		podaciDuznik.setModel(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciDuznik().getModel());
//		podaciDuznik.setPozivNaBroj(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciDuznik().getPozivNaBroj());
//		
//		korenski.soap.odobrenjertgs.TFinansijskiPodaci podaciPoverilac = new korenski.soap.odobrenjertgs.TFinansijskiPodaci();
//		podaciPoverilac.setBrojRacuna(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getBrojRacuna());
//		podaciPoverilac.setModel(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getModel());
//		podaciPoverilac.setPozivNaBroj(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getPozivNaBroj());
//		
//		korenski.soap.odobrenjertgs.RTGS.StavkaPrenosa sp = new korenski.soap.odobrenjertgs.RTGS.StavkaPrenosa();
//		korenski.soap.odobrenjertgs.RTGS.StavkaPrenosa.PodaciOPlacanju pop = new korenski.soap.odobrenjertgs.RTGS.StavkaPrenosa.PodaciOPlacanju();
//		pop.setDatumNaloga(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getDatumNaloga());
//		pop.setDatumValute(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getDatumValute());
//		pop.setOznakaValute(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getOznakaValute());
//		pop.setIznos(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getIznos());
//		pop.setFinansijskiPodaciDuznik(podaciDuznik);
//		pop.setFinansijskiPodaciPoverilac(podaciPoverilac);
//		
//		sp.setDuznikNalogodavac(request.getZahtev().getStavkaPrenosa().getDuznikNalogodavac());
//		sp.setPrimalacPoverilac(request.getZahtev().getStavkaPrenosa().getPrimalacPoverilac());
//		sp.setIDPoruke(request.getZahtev().getStavkaPrenosa().getIDPoruke());
//		sp.setPodaciOPlacanju(pop);
//		
//		RTGS rtgs = new RTGS();
//		rtgs.setBankaPoverilac(bPoverilac);
//		rtgs.setBankaDuznik(bDuznik);
//		rtgs.setDatum(request.getZahtev().getDatum());
//		rtgs.setIznos(request.getZahtev().getIznos());
//		rtgs.setSifraPrometa(request.getZahtev().getSifraPrometa());
//		rtgs.setStavkaPrenosa(sp);
//		
//		return rtgs;
//	}
//	
//	public korenski.soap.odobrenjertgs.Poruka makeRequestRtgsPoruka(RtgsRequest request){
//		
//		korenski.soap.odobrenjertgs.Poruka p = new korenski.soap.odobrenjertgs.Poruka();
//		
//		korenski.soap.odobrenjertgs.TBanka b = new korenski.soap.odobrenjertgs.TBanka();
//		b.setNaziv(request.getZahtev().getBankaPoverilac().getNaziv());
//		b.setObracunskiRacun(request.getZahtev().getBankaPoverilac().getObracunskiRacun());
//		b.setSwiftKod(request.getZahtev().getBankaPoverilac().getSwiftKod());
//		
//		p.setBanka(b);
//		//p.setDatumValute();
//		//p.setIdPoruke("");
//		//p.setIdPorukeNaloga(value);
//		p.setIznos(request.getZahtev().getIznos());
//		//p.setSifraVlute("DIN");
//		
//		return p;
//	}
	//deo za rtgs kraj
}