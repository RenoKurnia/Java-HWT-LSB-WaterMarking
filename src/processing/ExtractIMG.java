/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

/**
 *
 * @author Rico
 */

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.image.DataBufferByte;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.swing.JTextField;
import watermarks.MainMark;

/**
 *
 * @author alex
 */
public class ExtractIMG {
   
    
    public static void ExtractIMG(File origin, File target,String key)
    {
      
        
        //getting file extension
        try {
            
        String extension1 = "";
         
        
        int i = origin.getName().lastIndexOf('.');
        if (i > 0) {
        extension1 = origin.getName().substring(i+1).toLowerCase();
        }
                
        //A    
        BufferedImage image = ImageIO.read(origin);
        
        //source decompose
               String s = Stegan.decode(image);
               int[] dKey = ImageMatrixIO.keyDecode(key);
               double[][] mat = ImageMatrixIO.StringtoMatrix(s, dKey);
               if(ImageMatrixIO.succes==1){
               HaarWavelet2D mat2 = new HaarWavelet2D(mat);
               mat2.inverseDecomposition();
               double[][] result = mat2.getMatrix();           
               try{
               BufferedImage SS = ImageMatrixIO.matToImage(result);                   
               ImageIO.write(SS,extension1, target);
               JOptionPane.showMessageDialog(null, "Mark was Extracted.");
                System.out.println("No Problema!");
               }
               catch(Exception e){
                   JOptionPane.showMessageDialog(null,"An Error Has been Occured, Please Try Again!","Error", JOptionPane.ERROR_MESSAGE);
               }                              		                        
               }
        
        } catch (IOException ex) {
               System.err.println(ex);
               JOptionPane.showMessageDialog(null, "Error: You MUST select valid data in every field", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
   

    
    
}