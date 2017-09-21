package tw.edu.nutc.s13023047.descrypt_wallet.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TempFileService {

	public File tempSaveMultipartFile(MultipartFile multipartFile) throws IOException {
		File temp = generateTempFile();
		multipartFile.transferTo(temp);
		return temp;
	}
	
	protected File generateTempFile() throws IOException {
		File temp = File.createTempFile(TempFileService.class.getName(), ".tmp");
		return temp;
	}
}
