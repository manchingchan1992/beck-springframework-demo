package cn.org.polaris.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.w3c.dom.Element;

@Endpoint
public class HolidayEndPoint {
	private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "HolidayRequest")
	public void handleHolidayRequest(@RequestPayload Element holiday)throws Exception {
		System.out.println("####receive xml message######" + holiday.toString());
	}
}
