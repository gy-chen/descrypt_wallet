package tw.edu.nutc.s13023047.descrypt_wallet.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.CipherException;

import tw.edu.nutc.s13023047.descrypt_wallet.service.DecryptWalletService;
import tw.edu.nutc.s13023047.descrypt_wallet.service.TempFileService;

@Controller
public class DecryptWalletController {

	@Autowired
	protected DecryptWalletService mDecryptWalletService;

	@Autowired
	protected TempFileService mTempFileService;

	@GetMapping("/descrypt_wallet")
	public String descryptWalletPage() {
		return "descryptWalletPage";
	}

	@PostMapping("/descrypt_wallet")
	public String descryptWallet(@RequestParam("wallet") MultipartFile wallet,
			@RequestParam("password") String password, Model model) {
		try {
			File walletFile = mTempFileService.tempSaveMultipartFile(wallet);
			String privateKey = mDecryptWalletService.descryptWallet(password, walletFile);
			model.addAttribute("result", privateKey);
		} catch (CipherException e) {
			model.addAttribute("error", e.getMessage());
		} catch (IOException e) {
			model.addAttribute("error", "Incorrect wallet file");
		}
		return "descryptWalletResultPage";
	}
}
