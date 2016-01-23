package webApp;

import javax.jws.WebService;

@WebService
public interface IDriverWebService {
	public boolean addDriver(String name);
	public boolean editDriver(String name);
	public boolean deleteDriver(String name);
}
