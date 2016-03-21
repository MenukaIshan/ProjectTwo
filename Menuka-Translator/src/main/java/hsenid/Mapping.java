package hsenid;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class Mapping {
    /**
     * Main task of this class is the handle getLang.xml reply which is given by the yandex.
     */
    String inpFrom, inpTo;
    HashMap<String, String> data = new HashMap<String, String>();

    public Mapping() throws IOException, ParserConfigurationException, SAXException {

        String url = "https://translate.yandex.net/api/v1.5/tr/getLangs?key=trnsl.1.1.20160314T043532Z.7b2cd69323fcafb3.0e2a38f131f947f39dce80a89756c4d03ed5da6a&ui=en";

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();
        Document doc;

        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(entity.getContent());
            NodeList nodeList = doc.getElementsByTagName("Item");

            for (int x = 0; x < nodeList.getLength(); x++) {

                String key = nodeList.item(x).getAttributes().getNamedItem("key").getNodeValue();
                String value = nodeList.item(x).getAttributes().getNamedItem("value").getNodeValue();
                data.put(key, value);

            }
        }
    }

    public String[] sendMap(String from, String to) {

        String[] sent = new String[]{data.get(from), data.get(to)};

        return sent;
    }

    public HashMap GetData() {

        return (HashMap) data;
    }


    public String[] sendValues() {

        String[] arr = new String[data.size()];
        int i = 0;
        for (String key : data.values()) {
            arr[i] = key;
            i++;
        }
        return arr;

    }

}