package com.Quda.MailProyect.Controladores;

import com.Quda.MailProyect.Servicios.MailServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

@RestController
@AllArgsConstructor
@RequestMapping("/Mail")
public class MailControlador {

    private final MailServicio mailServicio;

    @PostMapping("/{M}/{S}/{B}")
    public HttpEntity<HttpStatus> enviarCorreo(@PathVariable String M ,@PathVariable String S,@PathVariable String B)
            throws MessagingException, UnsupportedEncodingException, FileNotFoundException {

        mailServicio.enviarMensaje(M,S,B);

        return new HttpEntity<>(HttpStatus.ACCEPTED);
    }


}
