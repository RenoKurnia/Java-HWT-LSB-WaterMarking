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
public class MarkingIMG {
   static String key;
    
    public static void MarkingIMG(File origin,File marking, File target)
    {
      
        
        //getting file extension
        try {
            
        String extension1 = "";
        String extension2 = "";
        
        int i = origin.getName().lastIndexOf('.');
        if (i > 0) {
        extension1 = origin.getName().substring(i+1).toLowerCase();
        }
        
        int j = marking.getName().lastIndexOf('.');
        if (j > 0) {
        extension2 = marking.getName().substring(j+1).toLowerCase();
        }
        //A    
        BufferedImage image = ImageIO.read(origin);
        BufferedImage image2 = ImageIO.read(marking);
        //source decompose
               try{double[][] imgData = ImageMatrixIO.imageToMat(image2);
               HaarWavelet2D mat = new HaarWavelet2D(imgData);
               mat.standardDecomposition();
               mat.show();
               System.out.println("");
               double[][] result =  mat.getMatrix();
               
               String hide = ImageMatrixIO.StringMaker(result);               
               image = Stegan.add_text(image, hide);
               if(Stegan.succes==1){                
               ImageIO.write(image,extension1,target);
               key = ImageMatrixIO.keyMaker(result);
               JOptionPane.showMessageDialog(null, "Mark was added.");
                    System.out.println("No Problema!");
                    }
               else{
                   
               }
               }
               catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Error: Try Other Image!", "Error", JOptionPane.ERROR_MESSAGE);
               }
		//mat.show();
                
         //B    
         /*
         double[][] imageData2 = ImageMatrixIO.imageToMat(image2);
         HaarWavelet2D matMark = new HaarWavelet2D(imageData2);
         matMark.standardDecomposition();
         //marker decompose
         double[][] result = ImageMatrixIO.marking(haarMat, matMark.getMatrix());
         HaarWavelet2D mat2 = new HaarWavelet2D(result);
         mat2.inverseDecomposition();
         double[][] result2 = mat2.getMatrix();
         ImageIO.write(ImageMatrixIO.matToImage(result2),extension1,target);
         */
         
         
            //double[][] imageData2 = ImageMatrixIO.imageToMat(image2);
            //ImageIO.write(ImageMatrixIO.matToImage(imageData2), extension2, target);
		
 
        // inicializo las propiedades de fuentes, color, transparencia
       // AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
     
        } catch (IOException ex) {
               System.err.println(ex);
               JOptionPane.showMessageDialog(null, "Error: You MUST select valid data in every field", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    public static String getKey(){
        return key;
    }

    
    
}