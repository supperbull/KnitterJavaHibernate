package com.example.antywir.service;

/**
 * Created by Redbullek on 2017-01-11.
 */

import com.example.antywir.domain.antywirus;
import com.example.antywir.domain.pakiet;

import java.util.List;


public interface Iantywirusmanager {

    Long addantywirus(antywirus antywirus);
    List<antywirus> Wszystkieantywirusy();
    antywirus findByIdantywirus(Long id);
    antywirus findByNazwa(String nazwa);
    void deleteantywirus(antywirus antywirus);



    Long addpakiet(pakiet pakiet);
    List<pakiet> WszystkiePakiety();
    pakiet findByIdpakiet(Long id);
    pakiet findBynazwa(String nazwa);
    void deletepakiet(pakiet pakiet);

    List<antywirus> allantywiruspakiet(Long idantywirus);
    void addpakiettoantywir(Long idantywirus,Long idpakiet);

}
