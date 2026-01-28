package goya.daw2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Mascota(Long id, String type, Double price) {

	@Override
	public String toString() {
		return "Mascota [id=" + id + ", type=" + type + ", price=" + price + "]";
	}
	
	
}
