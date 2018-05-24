import java.io.*;

public class cipher
{
	public static final String str = "abcdefghijklmnopqrstuvwxyz";
	
	public static String encrypt(String plaint , int key)
	{
		
		plaint = plaint.toLowerCase();
		String ciphert = "";
		
		for(int i=0;i<plaint.length();i++)
		{
			
			int charpos = str.indexOf(plaint.charAt(i));
			int keyval = (charpos + key) % 26;
			char replaceval = str.charAt(keyval);
			ciphert = ciphert + replaceval;
			
		}
		
		return ciphert;
	
	}

	public static String decrypt(String ciphert , int key)
	{
		
		ciphert = ciphert.toLowerCase();
		String plaint = "";
		
		for(int i=0;i<ciphert.length();i++)
		{
			
			int charpos = str.indexOf(ciphert.charAt(i));
			int keyval = (charpos - key) % 26;
			
			if(keyval<0)
			{
				
				keyval = str.length() + keyval;
				
			}
			
			char replaceval = str.charAt(keyval);
			plaint = plaint + replaceval;
			
		}
		
		return plaint;
	}

	public static void main(String args[])throws IOException
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter The Message: ");
		String msg = br.readLine();
		
		System.out.println("Encrypted Message: ");
		System.out.println(encrypt(msg,3));
		
		System.out.println("Decrypted Text: ");
		System.out.println(decrypt(encrypt(msg,3),3));
		
	}
}