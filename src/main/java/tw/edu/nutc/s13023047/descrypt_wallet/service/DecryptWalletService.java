package tw.edu.nutc.s13023047.descrypt_wallet.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

@Service
public class DecryptWalletService {

	protected final String FORMAT_PRIVATE_KEY = "0x%x";

	public String descryptWallet(String password, File source) throws IOException, CipherException {
		Credentials credentials = WalletUtils.loadCredentials(password, source);
		BigInteger privateKey = credentials.getEcKeyPair().getPrivateKey();
		return String.format(FORMAT_PRIVATE_KEY, privateKey);
	}
}
