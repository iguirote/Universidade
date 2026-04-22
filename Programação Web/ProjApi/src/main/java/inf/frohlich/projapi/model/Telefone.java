package inf.frohlich.projapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numero;
    @ManyToOne
    @JoinColumn(name = "Usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Telefone(int id, String numero, Usuario usuario) {
        this.id = id;
        this.numero = numero;
        this.usuario = usuario;
    }

    public Telefone(String numero, Usuario usuario) {
        this.numero = numero;
        this.usuario = usuario;
    }

    public Telefone() {
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
