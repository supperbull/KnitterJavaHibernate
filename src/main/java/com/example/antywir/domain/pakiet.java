package com.example.antywir.domain;

/**
 * Created by Redbullek on 2017-01-10.
 */

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name="pakiet.all", query = "select p from pakiet p"),
        @NamedQuery(name = "pakiet.bynazwa",query = "select p from pakiet p where p.funkcje=:funkcje "),
})
public class pakiet {

    private Long id_pakiet;
    private String nazwapakiet;
    private String funkcje;
    private Double cena;
    private Collection<antywirus> Pakiety;

    @Id
    @Column(name = "IDpakiet")
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

    @OneToMany(mappedBy = "pakiet",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    public Collection<antywirus> getantywirusy() {
        return Pakiety;
    }

    public void setantywirusy(Collection<antywirus> Pakiety) {
        this.Pakiety = Pakiety;
    }
}
