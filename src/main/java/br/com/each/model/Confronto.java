package br.com.each.model;

import java.sql.Date;


public class Confronto {

	private Long id;
	private Evento evento;
	private Date data;
	private Lutador lutador1;
	private Lutador lutador2;
	private Lutador vencedor;
	private Categoria categoria;
	private TipoVitoria tipoVitoria;

	public Confronto(Evento evento, Date data, Lutador lutador1, Lutador lutador2, Categoria categoria) {
		this.evento = evento;
		this.data = data;
		this.lutador1 = lutador1;
		this.lutador2 = lutador2;
		this.categoria = categoria;
	}

	public Lutador getVencedor() {
		return vencedor;
	}

	public void setVencedor(Lutador vencedor) {
		this.vencedor = vencedor;
	}

	public TipoVitoria getTipoVitoria() {
		return tipoVitoria;
	}

	public void setTipoVitoria(TipoVitoria tipoVitoria) {
		this.tipoVitoria = tipoVitoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public Date getData() {
		return data;
	}

	public Lutador getLutador1() {
		return lutador1;
	}

	public Lutador getLutador2() {
		return lutador2;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Confronto other = (Confronto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Confronto [id=" + id + ", evento=" + evento + ", data=" + data + ", lutador1=" + lutador1 + ", lutador2=" + lutador2 + ", vencedor=" + vencedor + ", categoria=" + categoria + ", tipoVitoria=" + tipoVitoria + "]";
	}

}
