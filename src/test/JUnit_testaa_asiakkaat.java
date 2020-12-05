package test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import model.Asiakas;
import model.dao.Dao;

@TestMethodOrder(OrderAnnotation.class)
class JUnit_testaa_asiakkaat {

	@Test
	@Order(1) 
	public void testPoistaKaikkiAsiakkaat() {
		//Poistetaan kaikki asiakkaat
		Dao dao = new Dao();
		dao.poistaKaikkiAsiakkaat("nimda");
		ArrayList<Asiakas> asiakkaat = dao.listaaKaikki();
		assertEquals(0, asiakkaat.size());
	}
	
	@Test
	@Order(2) 
	public void testLisaaAsiakas() {		
		//Tehdään muutama uusi testiasiakas
		Dao dao = new Dao();
		Asiakas asiakas_1 = new Asiakas(1, "Atte", "Aho", "12345", "a.a@gmail.com");
		Asiakas asiakas_2 = new Asiakas(2, "Petteri", "Piha", "23456", "p.p@gmail.com");
		Asiakas asiakas_3 = new Asiakas(3, "Mauno", "Mäki", "34567", "m.m@gmail.com");
		Asiakas asiakas_4 = new Asiakas(4, "Juha", "Jalka", "45678", "j.j@gmail.com");
		assertEquals(true, dao.lisaaAsiakas(asiakas_1));
		assertEquals(true, dao.lisaaAsiakas(asiakas_2));
		assertEquals(true, dao.lisaaAsiakas(asiakas_3));
		assertEquals(true, dao.lisaaAsiakas(asiakas_4));
	}
	
	@Test
	@Order(3) 
	public void testMuutaAsiakas() {
		//Muutetaan yhtä asiakasta
		Dao dao = new Dao();
		Asiakas muutettava = dao.etsiAsiakas(1);
		muutettava.setAsiakas_id(1);
		muutettava.setEtunimi("Manu");
		muutettava.setSukunimi("Mainio");
		muutettava.setPuhelin("1234");
		muutettava.setSposti("m.m@gmail.com");
		dao.muutaAsiakas(muutettava, 1);	
		assertEquals(1, dao.etsiAsiakas(1).getAsiakas_id());
		assertEquals("Manu", dao.etsiAsiakas(1).getEtunimi());
		assertEquals("Mainio", dao.etsiAsiakas(1).getSukunimi());
		assertEquals("1234", dao.etsiAsiakas(1).getPuhelin());
		assertEquals("m.m@gmail.com", dao.etsiAsiakas(1).getSposti());
	}
	
	@Test
	@Order(4) 
	public void testPoistaAsiakas() {
		Dao dao = new Dao();
		dao.poistaAsiakas(1);
		assertEquals(null, dao.etsiAsiakas(1));
	}

}
