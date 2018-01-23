package test.delegate;

@javax.jws.WebService(targetNamespace = "http://test.com/", serviceName = "demoService", portName = "demoPort", wsdlLocation = "WEB-INF/wsdl/demoService.wsdl")
public class demoDelegate {

	demo demo = new demo();

	public String getName(String name) {
		return demo.getName(name);
	}

}