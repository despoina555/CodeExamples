package org.despina;

import static org.junit.Assert.assertTrue;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Before // setup()
    public void before() throws Exception {
        System.out.println("Let's get started!");
    }

    @Test
    public void submitNTLMAuthRequest() throws IOException {
        ArrayList<String> credentials = new ArrayList<String>();
        credentials.add("username"); //username
        credentials.add("password");//password
        credentials.add("domain");//domain

        CloseableHttpClient httpClient = NtlmAuthImplemetation.constructNtlmAuthHttpClient(35,35,credentials);

        HttpGet httpGet = new HttpGet("path");
        httpGet.addHeader("Content-type", "application/json; charset=utf-8");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(200,response.getStatusLine().getStatusCode());

    }


    @Test
    public void getPDFFromString() throws IOException {
        String fileName=ConvertToPDF.fromStringToPDF();
        Assert.assertEquals("The Tragedy of Macbeth",fileName);
    }


    @Test
    public void buildAnExcelSheets() throws IOException {
        String headerContent = "Αριθμός Απόδειξης;Κωδ. Κατηγορίας Συμβολαίου;Ημ.Εκδοσης;Ημ.Έναρξης;Ημ.Λήξης;Ημ.Διακοπής;Αριθμος Κυκλοφορίας;Μικτά Ασφάλιστρα;Καθαρά Ασφάλιστρα;Συνολο Φόρων;Συνολο Δικαιωματος;Κωδικός Πακέτου;Κωδικός Πελάτη;Ονοματεπώνυμο Πελάτη;Διεύθυνση;Τ.Κ;Πόλη;Νομός;Περιοχή;Τηλέφωνο;Τηλέφωνο2;Φαξ;κινητο τηλεφωνο;Ημ.Γέννησης;Επάγγελμα;ΑΦΜ;Ταυτότητα;ΔΟΥ;Πατρώνυμο\nΑριθμός Απόδειξης;Κωδ. Κατηγορίας Συμβολαίου;Ημ.Εκδοσης;Ημ.Έναρξης;Ημ.Λήξης;Ημ.Διακοπής;Αριθμος Κυκλοφορίας;Μικτά Ασφάλιστρα;Καθαρά Ασφάλιστρα;Συνολο Φόρων;Συνολο Δικαιωματος;Κωδικός Πακέτου;Κωδικός Πελάτη;Ονοματεπώνυμο Πελάτη;Διεύθυνση;Τ.Κ;Πόλη;Νομός;Περιοχή;Τηλέφωνο;Τηλέφωνο2;Φαξ;κινητο τηλεφωνο;Ημ.Γέννησης;Επάγγελμα;ΑΦΜ;Ταυτότητα;ΔΟΥ;Πατρώνυμο";
        boolean success =ExcelSheets.buildExcelFromCSV(headerContent);
        Assert.assertTrue(success);

    }


}
