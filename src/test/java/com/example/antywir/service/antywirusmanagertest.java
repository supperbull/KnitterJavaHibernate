package com.example.antywir.service;

import com.example.antywir.domain.antywirus;
import com.example.antywir.domain.pakiet;
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
        assertEquals(PAKIECIK.getNazwapakiet(), iantywirusmanager.findByNazwa(PAKIECIK.getNazwapakiet()).getPakiety());
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
                                                                .getPakiety());

    }




}