package de.eorg.cumulusgenius.client.View;

public class ProviderData {
	private static ProviderRecord[] records;  
	  
    public static ProviderRecord[] getRecords() {  
        if (records == null) {  
            records = getNewRecords();  
        }  
        return records;  
    }  
  
    public static ProviderRecord[] getNewRecords() {  
        return new ProviderRecord[]{  
            new ProviderRecord("Amazon", "Beispielpreis", "Amazon.jpg", "Beispielbeschreibung"),
            new ProviderRecord("Rockspace", "Beispielpreis", "Rockspace.jpg", "Beispielbeschreibung")
        };
    }
}
        