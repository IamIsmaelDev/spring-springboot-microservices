package com.banana.bananawhatsapp.modelos;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="mensaje")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "from_user")
    private Usuario remitente;

    @ManyToOne()
    @JoinColumn(name = "to_user")
    private Usuario destinatario;

    private String cuerpo;
    private LocalDate fecha;

    // Getters y setters

    private boolean validarFecha() {
        return this.fecha != null && this.fecha.compareTo(LocalDate.now()) <= 0;
    }

    public boolean valido() throws MensajeException{
        if (remitente != null
                && destinatario != null
                && remitente.valido()
                && destinatario.valido()
                && cuerpo != null
                && cuerpo.length() > 10
                && validarFecha()
        ) return true;
        else throw new MensajeException("Mensaje no valido");
    }


}
