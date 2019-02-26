package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderContinueErrorException;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

public class UploadFile {
	private static final String ACCESS_TOKEN = "rS-96-zwnwAAAAAAAAAAERSUryckoOU-5YI_vjBnoEYSyWgnoXzEPjmfC0SOnNUP";
	private String pathUtente;
	public UploadFile(){
		
	}
	public void aggiungiSong(String percorsoCompleto,String nome) throws ListFolderContinueErrorException, DbxException, FileNotFoundException, IOException {
		 // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Upload file to Dropbox
        try (InputStream in = new FileInputStream(percorsoCompleto)) {
            FileMetadata metadata = client.files().uploadBuilder("/"+nome)
                .uploadAndFinish(in);
        }
        
        DbxDownloader<FileMetadata> downloader = client.files().download("/"+nome);
        try {
            FileOutputStream out = new FileOutputStream("C:\\Users\\"+System.getProperty("user.name")+"\\git\\WebComp\\WebContent\\SONG\\"+nome);
            downloader.download(out);
            out.close();
        } catch (DbxException ex) {
            System.out.println(ex.getMessage());
        }
        
        
	}
	public void riempiCartella() throws ListFolderErrorException, DbxException, IOException {
		 // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
            	 DbxDownloader<FileMetadata> downloader = client.files().download(metadata.getPathLower());
            	 String file=metadata.getPathDisplay().substring(metadata.getPathLower().lastIndexOf("/")+1);
                 try {
                     FileOutputStream out = new FileOutputStream("C:\\Users\\"+System.getProperty("user.name")+"\\git\\WebComp\\WebContent\\SONG\\"+file);
                     downloader.download(out);
                     out.close();
                 } catch (DbxException ex) {
                     System.out.println(ex.getMessage());
                 }
                 
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }

	}
}
