package com.example.antywir.service;

import com.example.antywir.domain.antywirus;
import com.example.antywir.domain.pakiet;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * Created by Redbullek on 2017-01-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class antywirusmanagertest {
    private String randomString="";

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Autowired
    private Iantywirusmanager iantywirusmanager;

    @Before
    public void setUp() throws Exception {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        randomString= sb.toString();
    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void addpakiet() throws Exception {
       pakiet PAKIECIK=new pakiet();
        PAKIECIK.setNazwapakiet("Ultimate");
        PAKIECIK.setfunkcje("Wszystkie");
        PAKIECIK.setcena(199.9);

        Long idM= iantywirusmanager.addpakiet(PAKIECIK);
        assertEquals(PAKIECIK.getNazwapakiet(), iantywirusmanager.findByNazwa(PAKIECIK.getNazwapakiet()).getPakiet());
    }
    @Test
    public void addantywirus() throws Exception {
        antywirus ANTYWIR=new antywirus();
        ANTYWIR.setnazwaantywirus(randomString);
        ANTYWIR.setOpis("Codzienny uzytek");
        ANTYWIR.setocena(3.5);
        iantywirusmanager.addantywirus(ANTYWIR);
        assertEquals(ANTYWIR.getnazwaantywirus(), iantywirusmanager
                                                                .findByNazwa(ANTYWIR.getnazwaantywirus())
                                                                .getPakiet());
    }


    @Test
    public void WszystkiePakiety() throws Exception {

        pakiet PAKIECIK=new pakiet();
        PAKIECIK.setNazwapakiet("Ultimate");
        PAKIECIK.setcena(199.99);
        PAKIECIK.setfunkcje("MAX");
        iantywirusmanager.addpakiet(PAKIECIK);
        List<pakiet> categories=iantywirusmanager.WszystkiePakiety();

        assertTrue(categories.size()>=1);
    }

    @Test
    public void findByIdpakiet() throws Exception {
        pakiet PAKIECIK=new pakiet();
        PAKIECIK.setNazwapakiet("Ultimate");
        PAKIECIK.setcena(199.99);
        PAKIECIK.setfunkcje("MAX");
        Long id=iantywirusmanager.addpakiet(PAKIECIK);
        assertEquals("Ultimate",iantywirusmanager.findByIdpakiet(id).getNazwapakiet());

    }
    @Test
    public void findbynazwat() throws Exception {
        pakiet PAKIECIK=new pakiet();
        PAKIECIK.setNazwapakiet("Ultimate");
        PAKIECIK.setcena(199.99);
        PAKIECIK.setfunkcje("MAX");
        iantywirusmanager.addpakiet(PAKIECIK);
        assertEquals("Ultimate",iantywirusmanager.findByNazwa(PAKIECIK.getNazwapakiet()));

    }

    @Test
    public void deletepakiet() throws Exception {

        pakiet PAKIECIK=new pakiet();
        PAKIECIK.setNazwapakiet("Ultimate");
        PAKIECIK.setcena(199.99);
        PAKIECIK.setfunkcje("MAX");
        Long paczka=iantywirusmanager.addpakiet(PAKIECIK);

        antywirus ANTYWIREK=new antywirus();
        ANTYWIREK.setnazwaantywirus("SUPER");
        ANTYWIREK.setOpis("EverydayUse");
        ANTYWIREK.setocena(5.5);
        ANTYWIREK.setPakiet(PAKIECIK);
        Long anty= iantywirusmanager.addantywirus(ANTYWIREK);
        //usuwanie pakietu
        iantywirusmanager.deletepakiet(PAKIECIK);
        boolean ifExists=false;
        try {
            assertTrue(iantywirusmanager.findByIdpakiet(paczka).getNazwapakiet()=="Ultimate");
        }catch (NullPointerException e)
        {
            ifExists=true;
        }
        assertTrue(ifExists);
        assertEquals("SUPER",
                iantywirusmanager
                        .findByIdantywirus(anty)
                        .getnazwaantywirus());


    }

    @Test
    public void addModel() throws Exception {
        antywirus ANTYWIREK=new antywirus();
        ANTYWIREK.setnazwaantywirus("HOME");
        ANTYWIREK.setOpis("EverydayUse");
        ANTYWIREK.setocena(3.5);

        Long idM= iantywirusmanager.addantywirus(ANTYWIREK);
        //test znajdowania antywirusa po id
        assertEquals("HOME",
                iantywirusmanager
                        .findByIdantywirus(idM)
                        .getnazwaantywirus());
    }

    @Test
    public void Wszystkieantywirusy() throws Exception {
        antywirus ANTYWIREK=new antywirus();
        ANTYWIREK.setnazwaantywirus("HOMEee");
        ANTYWIREK.setOpis("HOME");
        ANTYWIREK.setocena(3.5);

        antywirus ANTYWIREK2=new antywirus();
        ANTYWIREK2.setnazwaantywirus("HOMEee");
        ANTYWIREK2.setOpis("HOME");
        ANTYWIREK2.setocena(3.5);
        iantywirusmanager.addantywirus(ANTYWIREK);
        iantywirusmanager.addantywirus(ANTYWIREK2);
        List<antywirus> antywirusy=iantywirusmanager.Wszystkieantywirusy();
        assertTrue(antywirusy.size()>=2);
    }


    @Test
    public void findByNazwa() throws Exception {

        antywirus ANTYWIREK=new antywirus();
        ANTYWIREK.setnazwaantywirus("HOMEee");
        ANTYWIREK.setOpis("HOME");
        ANTYWIREK.setocena(3.5);
        iantywirusmanager.addantywirus(ANTYWIREK);
        assertEquals("HOMEee",iantywirusmanager
                                  .findByNazwa(ANTYWIREK.getnazwaantywirus())
                                    .getnazwaantywirus());
    }

    @Test
    public void deleteantywirus() throws Exception {
        antywirus ANTYWIREK=new antywirus();
        ANTYWIREK.setnazwaantywirus("HOMEee");
        ANTYWIREK.setOpis("HOME2");
        ANTYWIREK.setocena(3.5);
        iantywirusmanager.addantywirus(ANTYWIREK);

        antywirus ANTYWIREK2=new antywirus();
        ANTYWIREK2.setnazwaantywirus("HOMEee");
        ANTYWIREK2.setOpis("HOME");
        ANTYWIREK2.setocena(3.5);
        iantywirusmanager.addantywirus(ANTYWIREK2);
        Long idANT=iantywirusmanager.addantywirus(ANTYWIREK2);

        //usuwanie antywirusa
        iantywirusmanager.deleteantywirus(ANTYWIREK2);
        boolean ifExists=false;
        try {
            assertFalse(iantywirusmanager.findByIdantywirus(idANT).getOpis() != "HOME");
        }catch (NullPointerException e)
        {
            ifExists=true;
        }

        assertTrue(ifExists);
        //sprawdzamy czy nie zosatly usuniete inne rekordy
        assertTrue(iantywirusmanager.findByIdantywirus(idANT).getOpis()=="HOME2");
    }

    @Test
    public void getAllpakietModel() throws Exception {
        pakiet PAKIECIK=new pakiet();
        PAKIECIK.setNazwapakiet("Fajny");
        PAKIECIK.setcena(1.11);
        PAKIECIK.setfunkcje("Duzo");
        Long id=iantywirusmanager.addpakiet(PAKIECIK);

        antywirus ANTYWIREK=new antywirus();
        ANTYWIREK.setnazwaantywirus("HOME1");
        ANTYWIREK.setOpis("HOME1");
        ANTYWIREK.setocena(3.5);
        ANTYWIREK.setPakiet(PAKIECIK);

        antywirus ANTYWIREK3=new antywirus();
        ANTYWIREK3.setnazwaantywirus("HOME3");
        ANTYWIREK3.setOpis("HOME3");
        ANTYWIREK3.setocena(3.5);

        antywirus ANTYWIREK2=new antywirus();
        ANTYWIREK2.setnazwaantywirus("HOME2");
        ANTYWIREK2.setOpis("HOME2");
        ANTYWIREK2.setocena(3.5);
        ANTYWIREK2.setPakiet(PAKIECIK);

        iantywirusmanager.addantywirus(ANTYWIREK);
        iantywirusmanager.addantywirus(ANTYWIREK2);
        Long idANTYWIREK3=iantywirusmanager.addantywirus(ANTYWIREK3);
        List<antywirus> models=iantywirusmanager.allantywiruspakiet(id);
        assertTrue(models.size()==2);
        //test sprawdzajacy przypisanie pakietu do antywirusa w odpowiednie miejsce
        boolean ifExists=false;
        try {

            assertTrue(iantywirusmanager
                    .findByIdantywirus(idANTYWIREK3)
                    .getPakiet()
                    .getId() == id);
        }
        catch (Exception e){
            ifExists=true;

        }

        assertTrue(ifExists);
    }
    @Test
    public void addpakiettoantywir() throws Exception {
        pakiet PAKIECIK=new pakiet();
        PAKIECIK.setNazwapakiet("Fajny");
        PAKIECIK.setcena(1.11);
        PAKIECIK.setfunkcje("Duzo");
        Long idP=iantywirusmanager.addpakiet(PAKIECIK);
        antywirus ANTYWIREK=new antywirus();
        ANTYWIREK.setnazwaantywirus("HOME2");
        ANTYWIREK.setOpis("HOME2");
        ANTYWIREK.setocena(3.5);
        Long idA=iantywirusmanager.addantywirus(ANTYWIREK);
        iantywirusmanager.addpakiettoantywir(idP, idA);
        List<antywirus> models=iantywirusmanager.allantywiruspakiet(idP);
        assertTrue(models.size()!=0);
        assertEquals(idA,models.get(0).getId());
    }


}