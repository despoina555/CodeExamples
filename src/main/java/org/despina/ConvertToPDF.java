package org.despina;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ConvertToPDF {


    static String fromStringToPDF(){
        String inputString = "QUNUIEkKClNDRU5FIEkuIEEgZGVzZXJ0IHBsYWNlLgoKVGh1bmRlciBhbmQgbGlnaHRuaW5nLiBFbnRlciB0aHJlZSBXaXRjaGVzCkZpcnN0IFdpdGNoCldoZW4gc2hhbGwgd2UgdGhyZWUgbWVldCBhZ2FpbgpJbiB0aHVuZGVyLCBsaWdodG5pbmcsIG9yIGluIHJhaW4/ClNlY29uZCBXaXRjaApXaGVuIHRoZSBodXJseWJ1cmx5J3MgZG9uZSwKV2hlbiB0aGUgYmF0dGxlJ3MgbG9zdCBhbmQgd29uLgpUaGlyZCBXaXRjaApUaGF0IHdpbGwgYmUgZXJlIHRoZSBzZXQgb2Ygc3VuLgpGaXJzdCBXaXRjaApXaGVyZSB0aGUgcGxhY2U/ClNlY29uZCBXaXRjaApVcG9uIHRoZSBoZWF0aC4KVGhpcmQgV2l0Y2gKVGhlcmUgdG8gbWVldCB3aXRoIE1hY2JldGguCkZpcnN0IFdpdGNoCkkgY29tZSwgR3JheW1hbGtpbiEKU2Vjb25kIFdpdGNoClBhZGRvY2sgY2FsbHMuClRoaXJkIFdpdGNoCkFub24uCkFMTApGYWlyIGlzIGZvdWwsIGFuZCBmb3VsIGlzIGZhaXI6CkhvdmVyIHRocm91Z2ggdGhlIGZvZyBhbmQgZmlsdGh5IGFpci4KRXhldW50";
        String filename=null;
        FileOutputStream fop=null;
        byte[] decoder = Base64.getDecoder().decode(inputString);
        try{
            filename="The Tragedy of Macbeth";
            File file= new File("/Users/despina/Desktop/"+filename);
            fop = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            fop.write(decoder);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filename;
    }

}
