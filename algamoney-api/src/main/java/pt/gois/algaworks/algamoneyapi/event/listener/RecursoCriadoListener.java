package pt.gois.algaworks.algamoneyapi.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pt.gois.algaworks.algamoneyapi.event.RecursoCriadoEvent;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {
    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {

        Long codigo = recursoCriadoEvent.getCodigo();
        HttpServletResponse response = recursoCriadoEvent.getResponse();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").
                buildAndExpand(codigo).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
