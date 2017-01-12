package com.example.antywir.domain;

/**
 * Created by Redbullek on 2017-01-10.
 */

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = "antywirus.all", query = "select a from antywirus a"),
        @NamedQuery(name = "antywirus.byNazwa",query = "select a from antywirus a where a.nazwaantywirus= :name"),
        @NamedQuery(name = "antywirus.bypakiet",query = "select a from antywirus a where a.Pakiety= :Pakiety")

})
public class antywirus {

    private Long id_antywirus;
    private String nazwaantywirus;
    private String opis;
    private double ocena;
    private Collection<pakiet> Pakiety;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="IDantywirus")
    public Long getId() {
        return id_antywirus;
    }

    public void setId(Long id_antywirus) {
        this.id_antywirus = id_antywirus;
    }
    @Column(unique = true,nullable = false)
    public String getnazwaantywirus() {
        return nazwaantywirus;
    }

    public void setnazwaantywirus(String nazwaantywirus) {
        this.nazwaantywirus = nazwaantywirus;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getocena() {
        return ocena;
    }

    public void setocena(double ocena) {
        this.ocena = ocena;
    }
    @OneToMany(mappedBy = "antywirus",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    public Collection<pakiet> getPakiety() {
        return Pakiety;
    }

    public void setPakiety(Collection<pakiet> Pakiety) {
        this.Pakiety = Pakiety;
    }
}
