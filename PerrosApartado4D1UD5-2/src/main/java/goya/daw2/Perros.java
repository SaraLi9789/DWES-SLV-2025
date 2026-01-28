package goya.daw2;

import java.util.List;
import java.util.Map;

public class Perros {
	private String status;
	private Map<String, List<String>> message;
    
	public Perros(String status, Map<String, List<String>> message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	
	public Perros() {
		super();
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public Map<String, List<String>> getMessage() {
		return message;
	}

	public void setMessage(Map<String, List<String>> message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Perros [status=" + status + ", message=" + message + "]";
	}
    
}
