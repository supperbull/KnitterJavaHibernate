package com.example.antywir.domain;

/**
 * Created by Redbullek on 2017-01-10.
 */

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name="pakiet.all", query = "select p from pakiet p"),
        @NamedQuery(name = "pakiet.byfunkcje",query = "select p from pakiet p where p.funkcje=:funkcje "),
})
public class pakiet {

    private Long id_pakiet;
    private String nazwapakiet;
    private String funkcje;
    private Double cena;

    @Id
    @Column(name = "IDModel")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id_pakiet;
    }

    public void setId(Long id_pakiet) {
        this.id_pakiet = id_pakiet;
    }

    public String getNazwapakiet() {
        return nazwapakiet;
    }

    public void setNazwapakiet(String nazwapakiet) {
        this.nazwapakiet = nazwapakiet;
    }
    @Column(unique = true,nullable = false)
    public String getfunkcje() {
        return funkcje;
    }

    public void setfunkcje(String funkcje) {
        this.funkcje = funkcje;
    }

    public Double getcena() {
        return cena;
    }

    public void setcena(Double cena) {
        this.cena = cena;
    }

}
