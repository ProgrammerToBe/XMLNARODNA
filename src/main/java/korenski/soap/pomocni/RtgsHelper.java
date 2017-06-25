package korenski.soap.pomocni;

import korenski.soap.odobrenjertgs.OdobrenjeRtgs;
import korenski.soap.odobrenjertgs.RTGS;
import korenski.soap.rtgs.RtgsRequest;
import korenski.soap.rtgs.RtgsResponse;

public class RtgsHelper {
	
	

	public RtgsHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RtgsResponse makeResponseRtgsZaduzenje(RtgsRequest request){
		RtgsResponse response = new RtgsResponse();
		
		korenski.soap.rtgs.Poruka p = new korenski.soap.rtgs.Poruka();
		p.setBanka(request.getZahtev().getBankaDuznik());

		p.setDatumValute(request.getZahtev().getDatumValute());
		p.setIdPoruke("MT900");
		p.setIdPorukeNaloga("MT101");
		p.setIznos(request.getZahtev().getIznos());
		p.setSifraVlute(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getOznakaValute());
		
		response.setOdgovor(p);
		
		return response;
	}
	
	public korenski.soap.odobrenjertgs.OdobrenjeRequest makeRequestOdobrenjeRtgs(RtgsRequest request){
		
		OdobrenjeRtgs or = new OdobrenjeRtgs();
		
		or.setRtgsElement(repackRtgs(request));
		or.setPorukaElement(makeRequestRtgsPoruka(request));
		
		korenski.soap.odobrenjertgs.OdobrenjeRequest oreq = new korenski.soap.odobrenjertgs.OdobrenjeRequest();
		oreq.setZahtev(or);
		
		return oreq;
	}
	
	public RTGS repackRtgs(RtgsRequest request){
		korenski.soap.odobrenjertgs.TBanka bPoverilac = new korenski.soap.odobrenjertgs.TBanka();
		bPoverilac.setNaziv(request.getZahtev().getBankaPoverilac().getNaziv());
		bPoverilac.setObracunskiRacun(request.getZahtev().getBankaPoverilac().getObracunskiRacun());
		bPoverilac.setSwiftKod(request.getZahtev().getBankaPoverilac().getSwiftKod());
		
		korenski.soap.odobrenjertgs.TBanka bDuznik = new korenski.soap.odobrenjertgs.TBanka();
		bDuznik.setNaziv(request.getZahtev().getBankaDuznik().getNaziv());
		bDuznik.setObracunskiRacun(request.getZahtev().getBankaDuznik().getObracunskiRacun());
		bDuznik.setSwiftKod(request.getZahtev().getBankaDuznik().getSwiftKod());
		
		korenski.soap.odobrenjertgs.TFinansijskiPodaci podaciDuznik = new korenski.soap.odobrenjertgs.TFinansijskiPodaci();
		podaciDuznik.setBrojRacuna(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciDuznik().getBrojRacuna());
		podaciDuznik.setModel(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciDuznik().getModel());
		podaciDuznik.setPozivNaBroj(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciDuznik().getPozivNaBroj());
		
		korenski.soap.odobrenjertgs.TFinansijskiPodaci podaciPoverilac = new korenski.soap.odobrenjertgs.TFinansijskiPodaci();
		podaciPoverilac.setBrojRacuna(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getBrojRacuna());
		podaciPoverilac.setModel(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getModel());
		podaciPoverilac.setPozivNaBroj(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getPozivNaBroj());
		
		korenski.soap.odobrenjertgs.RTGS.StavkaPrenosa sp = new korenski.soap.odobrenjertgs.RTGS.StavkaPrenosa();
		korenski.soap.odobrenjertgs.RTGS.StavkaPrenosa.PodaciOPlacanju pop = new korenski.soap.odobrenjertgs.RTGS.StavkaPrenosa.PodaciOPlacanju();
		pop.setDatumNaloga(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getDatumNaloga());
		pop.setOznakaValute(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getOznakaValute());
		pop.setIznos(request.getZahtev().getStavkaPrenosa().getPodaciOPlacanju().getIznos());
		pop.setFinansijskiPodaciDuznik(podaciDuznik);
		pop.setFinansijskiPodaciPoverilac(podaciPoverilac);
		
		sp.setDuznikNalogodavac(request.getZahtev().getStavkaPrenosa().getDuznikNalogodavac());
		sp.setPrimalacPoverilac(request.getZahtev().getStavkaPrenosa().getPrimalacPoverilac());
		sp.setIDPoruke(request.getZahtev().getStavkaPrenosa().getIDPoruke());
		sp.setPodaciOPlacanju(pop);
		
		RTGS rtgs = new RTGS();
		rtgs.setBankaPoverilac(bPoverilac);
		rtgs.setBankaDuznik(bDuznik);
		rtgs.setIznos(request.getZahtev().getIznos());
		rtgs.setSifraPrometa(request.getZahtev().getSifraPrometa());
		rtgs.setStavkaPrenosa(sp);
		
		return rtgs;
	}
	
	public korenski.soap.odobrenjertgs.Poruka makeRequestRtgsPoruka(RtgsRequest request){
		
		korenski.soap.odobrenjertgs.Poruka p = new korenski.soap.odobrenjertgs.Poruka();
		
		korenski.soap.odobrenjertgs.TBanka b = new korenski.soap.odobrenjertgs.TBanka();
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
