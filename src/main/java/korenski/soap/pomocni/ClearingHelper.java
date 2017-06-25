package korenski.soap.pomocni;

import org.springframework.context.annotation.Bean;

import korenski.soap.clearing.ClearingRequest;
import korenski.soap.clearing.ClearingResponse;
import korenski.soap.clearing.Poruka;
import korenski.soap.odobrenjeclearing.Clearing;
import korenski.soap.odobrenjeclearing.Clearing.StavkePrenosa;
import korenski.soap.odobrenjeclearing.Clearing.StavkePrenosa.StavkaPrenosa;
import korenski.soap.odobrenjeclearing.Clearing.StavkePrenosa.StavkaPrenosa.PodaciOPlacanju;
import korenski.soap.odobrenjeclearing.TBanka;
import korenski.soap.odobrenjeclearing.TFinansijskiPodaci;

public class ClearingHelper {

	public ClearingHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClearingResponse makeResponseClearingZaduzenje(ClearingRequest request){
		ClearingResponse response = new ClearingResponse();
		
		Poruka p = new Poruka();
		p.setBanka(request.getZahtev().getBankaDuznik());
		
		p.setDatumValute(request.getZahtev().getDatumValute());
		p.setIdPoruke("MT900");
		p.setIdPorukeNaloga("MT102");
		p.setIznos(request.getZahtev().getIznos());
		p.setSifraVlute(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(0).getPodaciOPlacanju().getOznakaValute());
		response.setOdgovor(p);
		
		return response;
	}
	
	public korenski.soap.odobrenjeclearing.OdobrenjeRequest makeRequestOdobrenjeClearing(ClearingRequest request){
		
		korenski.soap.odobrenjeclearing.OdobrenjeClearing oc = new korenski.soap.odobrenjeclearing.OdobrenjeClearing();
		
		oc.setClearingElement(repackClearing(request));
		oc.setPorukaElement(makeRequestClearingPoruka(request));
		
		korenski.soap.odobrenjeclearing.OdobrenjeRequest or = new korenski.soap.odobrenjeclearing.OdobrenjeRequest();
		or.setZahtev(oc);
		
		return or;
	}
	
	public Clearing repackClearing(ClearingRequest request){
		TBanka bPoverilac = new TBanka();
		bPoverilac.setNaziv(request.getZahtev().getBankaPoverilac().getNaziv());
		bPoverilac.setObracunskiRacun(request.getZahtev().getBankaPoverilac().getObracunskiRacun());
		bPoverilac.setSwiftKod(request.getZahtev().getBankaPoverilac().getSwiftKod());
		
		TBanka bDuznik = new TBanka();
		bDuznik.setNaziv(request.getZahtev().getBankaDuznik().getNaziv());
		bDuznik.setObracunskiRacun(request.getZahtev().getBankaDuznik().getObracunskiRacun());
		bDuznik.setSwiftKod(request.getZahtev().getBankaDuznik().getSwiftKod());
		
		StavkePrenosa stavkePrenosa = new StavkePrenosa();
		for(int i=0; i < request.getZahtev().getStavkePrenosa().getStavkaPrenosa().size(); i++){
			StavkaPrenosa sp = new StavkaPrenosa();
			sp.setDuznikNalogodavac(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getDuznikNalogodavac());
			sp.setPrimalacPoverilac(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPrimalacPoverilac());
			sp.setIDPoruke(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getIDPoruke());
			sp.setSvrhaPlacanja(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getSvrhaPlacanja());
			
			TFinansijskiPodaci podaciDuznik = new TFinansijskiPodaci();
			podaciDuznik.setBrojRacuna(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciDuznik().getBrojRacuna());
			podaciDuznik.setModel(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciDuznik().getModel());
			podaciDuznik.setPozivNaBroj(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciDuznik().getPozivNaBroj());
			
			TFinansijskiPodaci podaciPoverilac = new TFinansijskiPodaci();
			podaciPoverilac.setBrojRacuna(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getBrojRacuna());
			podaciPoverilac.setModel(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getModel());
			podaciPoverilac.setPozivNaBroj(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getPozivNaBroj());
			
			PodaciOPlacanju pop = new PodaciOPlacanju();
			
			pop.setDatumNaloga(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getDatumNaloga());
			pop.setOznakaValute(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getOznakaValute());
			pop.setIznos(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPodaciOPlacanju().getIznos());
			pop.setFinansijskiPodaciDuznik(podaciDuznik);
			pop.setFinansijskiPodaciPoverilac(podaciPoverilac);
			
			sp.setPodaciOPlacanju(pop);
			sp.setDuznikNalogodavac(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getDuznikNalogodavac());
			sp.setPrimalacPoverilac(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getPrimalacPoverilac());
			sp.setIDPoruke(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getIDPoruke());
			sp.setSvrhaPlacanja(request.getZahtev().getStavkePrenosa().getStavkaPrenosa().get(i).getSvrhaPlacanja());
			
			stavkePrenosa.getStavkaPrenosa().add(sp);
		}
		
		korenski.soap.odobrenjeclearing.Clearing c = new korenski.soap.odobrenjeclearing.Clearing();
		c.setBankaPoverilac(bPoverilac);
		c.setBankaDuznik(bDuznik);
		c.setDatum(request.getZahtev().getDatum());
		c.setIznos(request.getZahtev().getIznos());
		c.setSifraPrometa(request.getZahtev().getSifraPrometa());
		c.setStavkePrenosa(stavkePrenosa);
		
		return c;
	}
	
	public korenski.soap.odobrenjeclearing.Poruka makeRequestClearingPoruka(ClearingRequest request){
		
		korenski.soap.odobrenjeclearing.Poruka p = new korenski.soap.odobrenjeclearing.Poruka();
		
		TBanka b = new TBanka();
		b.setNaziv(request.getZahtev().getBankaPoverilac().getNaziv());
		b.setObracunskiRacun(request.getZahtev().getBankaPoverilac().getObracunskiRacun());
		b.setSwiftKod(request.getZahtev().getBankaPoverilac().getSwiftKod());
		
		p.setBanka(b);
		//p.setDatumValute();
		//p.setIdPoruke("");
		//p.setIdPorukeNaloga(value);
		p.setIznos(request.getZahtev().getIznos());
		//p.setSifraVlute("DIN");
		
		return p;
	}
	
}
