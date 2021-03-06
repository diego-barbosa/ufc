package br.com.each.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.each.ConnectionFactory.ConnectionFactory;
import br.com.each.model.Perfil;
import br.com.each.model.Usuario;
import br.com.each.model.UsuarioRepresentation;

@Component
public class UsuarioDAO {

	private Connection connection;

	private PreparedStatement pstm;

	public UsuarioDAO() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void salva(Usuario usuario) {
		try {
			this.connection = ConnectionFactory.getConnection();
			pstm = this.connection
					.prepareStatement("insert into tb_usuario (cpf, nome, login, senha, email, perfil) values (?,?,?,?,?,?)");
			pstm.setString(1, usuario.getCpf());
			pstm.setString(2, usuario.getNome());
			pstm.setString(3, usuario.getLogin());
			pstm.setString(4, usuario.getSenha());
			pstm.setString(5, usuario.getEmail());
			pstm.setString(6, usuario.getPerfil().toString());

			pstm.executeUpdate();

			pstm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void altera(Usuario usuario) {
		try {
			this.connection = ConnectionFactory.getConnection();
			pstm = this.connection
					.prepareStatement("update tb_usuario set cpf = ?, nome = ?, login = ?, senha = ?, email = ?, perfil = ? where cod_usuario = ?");
			pstm.setString(1, usuario.getCpf());
			pstm.setString(2, usuario.getNome());
			pstm.setString(3, usuario.getLogin());
			pstm.setString(4, usuario.getSenha());
			pstm.setString(5, usuario.getEmail());
			pstm.setString(6, usuario.getPerfil().toString());
			pstm.setLong(7, usuario.getId());

			pstm.executeUpdate();

			pstm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(Long id) {
		try {
			this.connection = ConnectionFactory.getConnection();
			pstm = this.connection
					.prepareStatement("delete from tb_usuario where cod_usuario = ?");
			pstm.setLong(1, id);
			pstm.executeUpdate();

			pstm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<UsuarioRepresentation> ranking() {
		List<UsuarioRepresentation> usuarios = new ArrayList<UsuarioRepresentation>();
		try {
			this.connection = ConnectionFactory.getConnection();
			pstm = this.connection
					.prepareStatement("SELECT"
							+ " COUNT(a.cod_usuario) as pontos,"
							+ " u.cod_usuario, "
							+ " u.cpf,"
							+ " u.nome,"
							+ " u.login,"
							+ " u.email,"
							+ " u.perfil"
							+ " FROM "
							+ "     tb_usuario u "
							+ "     INNER JOIN tb_aposta a "
							+ "         ON u.cod_usuario = a.cod_usuario "
							+ "     INNER JOIN tb_confronto c "
							+ "         ON c.cod_confronto = a.cod_confronto AND a.cod_lutador = c.cod_vencedor "
							+ " GROUP BY u.cod_usuario "
							+ " ORDER BY pontos DESC ");
			ResultSet set = pstm.executeQuery();
			while (set.next()) {
				UsuarioRepresentation representatioj = new UsuarioRepresentation();
				representatioj.setId(set.getLong("cod_usuario"));
				representatioj.setLogin(set.getString("login"));
				representatioj.setEmail(set.getString("email"));
				representatioj.setNome(set.getString("nome"));
				representatioj.setCpf(set.getString("cpf"));
				representatioj.setPerfil(set.getInt("perfil"));
				representatioj.setPontos(set.getLong("pontos"));
				usuarios.add(representatioj);
			}
			pstm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	public Usuario valida(Usuario usuario) {
		try {
			this.connection = ConnectionFactory.getConnection();
			pstm = this.connection
					.prepareStatement("select * from tb_usuario where login = ?");
			pstm.setString(1, usuario.getLogin());
			pstm.execute();

			ResultSet set = pstm.executeQuery();
			Usuario target = null;
			while (set.next()) {
				target = new Usuario(set.getString("nome"),
						set.getDate("dataNascimento"), set.getString("cpf"),
						set.getString("email"), set.getString("login"),
						set.getString("senha"), Perfil.valueOf(set
								.getString("perfil")));
				usuario.setId(set.getLong("cod_usuario"));
			}

			pstm.close();
			connection.close();
			if (usuario.getLogin().equals(target.getLogin())
					&& usuario.getSenha().equals(target.getSenha())) {
				target.setSenha(null);
				return target;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Usuario buscaPoId(Long id) {
		try {
			this.connection = ConnectionFactory.getConnection();
			pstm = this.connection
					.prepareStatement("select * from tb_usuario where cod_usuario = ?");
			pstm.setLong(1, id);
			pstm.execute();

			ResultSet set = pstm.executeQuery();
			while (set.next()) {
				Usuario usuario = new Usuario(set.getString("nome"),
						set.getDate("dataNascimento"), set.getString("cpf"),
						set.getString("email"), set.getString("login"),
						Perfil.valueOf(set.getString("perfil")));
				usuario.setId(set.getLong("cod_usuario"));
				return usuario;
			}

			pstm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}