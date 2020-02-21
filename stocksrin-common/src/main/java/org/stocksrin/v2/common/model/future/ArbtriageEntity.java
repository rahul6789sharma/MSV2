package org.stocksrin.v2.common.model.future;

public class ArbtriageEntity {

	private String symbole;
	private String expiry;

	public ArbtriageEntity(String symbole, String expiry) {
		super();
		this.symbole = symbole;
		this.expiry = expiry;
	}

	public String getSymbole() {
		return symbole;
	}

	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expiry == null) ? 0 : expiry.hashCode());
		result = prime * result + ((symbole == null) ? 0 : symbole.hashCode());
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
		ArbtriageEntity other = (ArbtriageEntity) obj;
		if (expiry == null) {
			if (other.expiry != null)
				return false;
		} else if (!expiry.equals(other.expiry))
			return false;
		if (symbole == null) {
			if (other.symbole != null)
				return false;
		} else if (!symbole.equals(other.symbole))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArbtriageEntity [symbole=" + symbole + ", expiry=" + expiry + "]";
	}

	
}
