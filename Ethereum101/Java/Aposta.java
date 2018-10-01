import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

public class Aposta {

	public static void main(String[] args) {
		
		//Conectar no Infura
		Web3j web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/24492f0df2674e6ca03c157c474027ee"));
		try
		{
			System.out.println(web3.web3ClientVersion().send().getWeb3ClientVersion());
		}
		catch(Exception e)
		{
			System.err.println(e);
			System.exit(1);
		}
		
		//Criar uma wallet pegando dados da chave publica
		Credentials credentials;
		String pk = "";
		
		Scanner in = new Scanner(System.in);
		do {
			System.out.print("\nDigite sua Private Key: ");
			if(in.hasNext()) pk = in.next();
		}	
		while(!WalletUtils.isValidPrivateKey(pk));
		//in.close();
		credentials = Credentials.create(pk);
		
		imprimirSaldo(credentials, web3);
		
		int opcao = 0;
		//in = new Scanner(System.in);
		do {
			imprimirMenu();
			if(in.hasNextInt())opcao = in.nextInt();
			
			switch(opcao)
			{
				case 1: criarContrato(credentials, web3);
				case 2: apostarContrato(credentials, web3, in);
				case 3: imprimirSaldo(credentials, web3);
			}
		}while(opcao != 4);
		
		in.close();
		web3.shutdown(); 
		System.exit(0);
	}

	
	private static void criarContrato(Credentials c, Web3j w)
	{
		try {
			Jogo contrato = Jogo.deploy(w, c, new BigInteger("5000"), new BigInteger("3000000")).send();
			System.out.println("\n" + "Endereço do novo contrato: " + contrato.getContractAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void apostarContrato(Credentials c, Web3j w, Scanner in)
	{
		Jogo j;
		String add = "";
		boolean valido = false;
		do {
			System.out.print("\n" + "Digite o endereço do contrato: ");
			if(in.hasNext()) add = in.next();
			j = Jogo.load(add, w, c, new BigInteger("5000"), new BigInteger("3000000"));
			
			try {
				valido = j.isValid();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		while(!valido);

		int valor = 0;
		System.out.print("\n" + "Digite o valor: ");
		if(in.hasNextInt()) valor = in.nextInt();
		try {
			j.apostar(new BigInteger(Integer.toString(valor))).send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void imprimirSaldo(Credentials c, Web3j w)
	{
		try {
			EthGetBalance balance = w.ethGetBalance(c.getAddress(), DefaultBlockParameterName.LATEST).send();
			System.out.println("Public Key: "+ c.getAddress());
			System.out.println("Saldo: "+ balance.getBalance());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void imprimirMenu()
	{
		System.out.println("\nDigite 1 para criar um novo contrato");
		System.out.println("Digite 2 para acessar um contrato existente e apostar");
		System.out.println("Digite 3 para verificar seu saldo");
		System.out.println("Digite 4 para Sair");
	}
	

}








