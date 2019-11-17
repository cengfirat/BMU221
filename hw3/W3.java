/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3;

import java.util.Scanner;

/**
 *
 * @author mehta
 */
public class W3 {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {  
        Scanner k = new Scanner(System.in); 
        STACK islem = new STACK();
       
        while (true) {            
            System.out.println("Lütfen ifade Giriniz: ");
            String ifade = k.nextLine();
            islem.kontrol(ifade);
        }
         
    } 
    
}
