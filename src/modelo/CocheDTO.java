package modelo;

public class CocheDTO {

	private String numeroBastidor;
	private String marca;
	private String modelo;
	private String color;
	private String regex = "^[A-Z0-9]{17}$";

	public CocheDTO(String numeroBastidor, String marca, String modelo, String color) throws CocheException {
		if(numeroBastidor.matches(regex)) {
			this.numeroBastidor = numeroBastidor;
			this.marca = marca;
			this.modelo = modelo;
			this.color = color;
		}else {
			throw new CocheException("Coche con numeo de bastidor no valido");
		}
	}

	public String getNumeroBastidor() {
		return numeroBastidor;
	}

	public void setNumeroBastidor(String numeroBastidor) {
		this.numeroBastidor = numeroBastidor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "CocheDTO [numeroBastidor=" + numeroBastidor + ", marca=" + marca + ", modelo=" + modelo + ", color="
				+ color + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroBastidor == null) ? 0 : numeroBastidor.hashCode());
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
		CocheDTO other = (CocheDTO) obj;
		if (numeroBastidor == null) {
			if (other.numeroBastidor != null)
				return false;
		} else if (!numeroBastidor.equals(other.numeroBastidor))
			return false;
		return true;
	}




}
