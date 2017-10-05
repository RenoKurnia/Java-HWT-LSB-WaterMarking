/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.swing.JOptionPane;


public class ImageMatrixIO {
    
    static int height=0;
    static int width=0;
    static int succes =0;
	public static BufferedImage matToImage(double[][] mat){
		int w = mat[0].length;
		int h = mat.length;
                
		
		BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		//to Grayscale
                
		for( int i = 0 ; i < w ; i++){
			for (int j =  0 ; j < h ; j++){
				int value = (int) Math.abs(mat[i][j]);
                                
     				//int color =value;
                                //int rgb = (int)mat[i][j]<< 24 |(int)mat[i][j]<< 16 | (int)mat[i][j] << 8 | (int)mat[i][j];
                                /*  
				if (value > 5){
					color = 0xFFFFFF;
				}
				else {
					color = 0x00;
				}
                                */
                                img.setRGB(i, j, -value);
			}
		}
                return img;
                
        }

	
	public static double[][] imageToMat(BufferedImage img){
		// Initializing the matrix
		double[][] mat = new double[img.getHeight()][img.getWidth()];
		
		// Filling the matrix
		for (int i = 0 ; i < img.getWidth() ; i++){
			for (int j = 0 ; j < img.getHeight() ; j++){
				Color grayColor = new Color(img.getRGB(i,j));	
				mat[i][j] = (double) grayColor.getRGB();
			}
		}
		return mat;
	}
        
      public static String StringMaker(double[][] mat){
		int w = mat[0].length;
		int h = mat.length;
                StringBuilder sb = new StringBuilder();
		
		for( int i = 0 ; i < w ; i++){
                    
			for (int j =  0 ; j < h ; j++){
                            sb.append(mat[i][j]);
                            sb.append(" ");
			}
		}
		return sb.toString();
	}
      
      public static String keyMaker(double[][] mat){
		int w = mat[0].length;
		int h = mat.length;
               StringBuilder sb  = new StringBuilder();
               sb.append(w);
               sb.append(" ");
               sb.append(h);
                       
		return sb.toString();
	}
      
      
      
      
      public static int[] keyDecode(String key){
		int []akey = new int[2];
                int i=0;
                for(String each:key.split(" ")){
                    akey[i] = Integer.parseInt(each);                           
                    i++;
                }
		return akey;
                }  
                

      
       
        public static double[][] StringtoMatrix(String ss,int[] key){
            
            int w = key[0];
            int h = key[1];
            double mat[][] = new double[w][h];
            int i=0;
            int j=0;
            StringBuilder sb = new StringBuilder();
            try{
            for(String eachA:ss.split(" ")){
                        mat[i][j] = Double.parseDouble(eachA);
                        j++;
                        if(j==h){
                            i++;
                            j=0;
                        }
		}
            succes =1;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Key doesnt Match!");
                succes =0;
            }
            return mat;
	}
                 
}

