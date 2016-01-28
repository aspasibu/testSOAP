package org.aspasibu.logitest.webservices;

import javax.jws.WebService;

import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.service.DriverService;

@WebService(endpointInterface = "org.aspasibu.logitest.webservices.DriverWebService")
public class DriverWebServiceImpl implements DriverWebService {

    private DriverService ds;
    
    @Override
    public void deleteDriver(Long idDriver) {
	ds.delete(idDriver);
    }
    
    @Override
    public Long addDriver(Driver driver) {
	return ds.addDriver(driver);
    }
    
    @Override
    public boolean editDriver(Driver driver) {
	return ds.editDriver(driver);
    }

    /**
     * @return the ds
     */
    public DriverService getDs() {
	return ds;
    }

    /**
     * @param ds
     *            the ds to set
     */
    public void setDs(DriverService ds) {
	this.ds = ds;
    }

}
