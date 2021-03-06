package br.com.each.Controller;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.each.dao.EventoDAO;
import br.com.each.model.Evento;

@Resource
public class EventoController {

	private Result result;
	private EventoDAO eventoDAO;

	public EventoController(Result result, EventoDAO eventoDAO) {
		this.result = result;
		this.eventoDAO = eventoDAO;
	}

	@Post("/eventos")
	@Consumes("application/json")
	public void salva(Evento evento) {
		eventoDAO.salva(evento);
		result.nothing();
	}

	@Put("/eventos")
	@Consumes("application/json")
	public void altera(Evento evento) {
		eventoDAO.altera(evento);
		result.nothing();
	}

	@Get("eventos/abertos")
	public void abertos() {
		result.use(Results.json()).withoutRoot().from(eventoDAO.listaAbertos()).serialize();
	}

	@Get("eventos/fechados")
	public void fechados() {
		result.use(Results.json()).withoutRoot().from(eventoDAO.listaFechados()).serialize();
	}

	@Get("eventos/finalizados")
	public void finalizados() {
		result.use(Results.json()).withoutRoot().from(eventoDAO.listaFinalizados()).serialize();
	}

}
