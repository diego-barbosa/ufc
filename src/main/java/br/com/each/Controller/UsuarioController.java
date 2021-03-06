package br.com.each.Controller;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.each.annotation.Public;
import br.com.each.dao.UsuarioDAO;
import br.com.each.model.UserSession;
import br.com.each.model.Usuario;

@Resource
public class UsuarioController {

	private Result result;
	private UsuarioDAO usuarioDAO;
	private UserSession session;

	public UsuarioController(Result result, UsuarioDAO usuarioDAO, UserSession session) {
		this.result = result;
		this.usuarioDAO = usuarioDAO;
		this.session = session;
	}

	@Post("/usuarios")
	@Consumes("application/json")
	public void save(Usuario usuario) {
		usuarioDAO.salva(usuario);
		loga(usuario);
		result.nothing();
	}

	@Put("/usuarios/{id}")
	@Consumes("application/json")
	public void update(Usuario usuario, Long id) {
		usuarioDAO.altera(usuario);
		result.nothing();
	}

	@Get("/perfil")
	public void perfil() {
		result.use(Results.json()).withoutRoot().from(usuarioDAO.buscaPoId(session.getUsuario().getId())).serialize();
	}

	@Get("/usuarios/{id}")
	public void perfil(Long id) {
		result.use(Results.json()).withoutRoot().from(usuarioDAO.buscaPoId(id)).serialize();
	}

	@Delete("/usuarios/{id}")
	public void remove(Long id) {
		usuarioDAO.remove(id);
		result.nothing();
	}

	@Get("/ranking")
	public void ranking() {
		result.use(Results.json()).withoutRoot().from(usuarioDAO.ranking()).serialize();
	}

	// @Post("/usuarios/foto")
	// public void salva(UploadedFile filename) {
	// fileManager.upload(filename, "usuarios" + File.separator +
	// session.getUsuario().getId());
	// result.nothing();
	// }
	//
	// @Get("/usuarios/foto")
	// public File foto() {
	// return fileManager.download("usuarios" + File.separator +
	// session.getUsuario().getId());
	// }

	@Public
	@Post("/logar")
	@Consumes("application/json")
	public void logar(Usuario usuario) {

		usuario = loga(usuario);

		result.use(Results.json()).withoutRoot().from(usuario).serialize();
	}

	@Get("/deslogar")
	public void deslogar() {
		session.setUsuario(null);
		result.nothing();
	}

	private Usuario loga(Usuario usuario) {
		usuario = usuarioDAO.valida(usuario);

		if (usuario != null) {
			session.setUsuario(usuario);
		}
		
		return usuario;
	}
}