package com.platauto.customadapter;

import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MostrarPedidos {
	
	private final String NAMESPACE = "http://www.webserviceX.NET/";
    private final String METHOD_NAME = "GetQuote";
    private final String SOAP_ACTION = "http://www.webserviceX.NET/GetQuote";
    private final String URL = "http://www.webservicex.net/stockquote.asmx";
 
    private SoapSerializationEnvelope envelope;
    
    public void StockQuoteFetcher(String quotes)
    {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
     
        PropertyInfo quotesProperty = new PropertyInfo();
        quotesProperty.setName("symbol");
        quotesProperty.setValue(quotes);
        quotesProperty.setType(String.class);
        request.addProperty(quotesProperty);
     
        envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
    }
    
    /**public List<StockQuote> Fetch();
    public String Fetch();
    {
        String result = "";
        HttpTransportSE httpRequest = new HttpTransportSE(URL);
     
        try
        {
            httpRequest.call(SOAP_ACTION, envelope);
            SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
            result =  response.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }**/
    
}