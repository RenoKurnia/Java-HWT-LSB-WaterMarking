/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import javax.swing.JOptionPane;

/**
 *
 * @author William Wilson
 * @version 1.6 python 3
 * Created May 8,2016
 */
public class Stegan {
    static int succes=0;
    
    
    private  static byte[] get_byte_data(BufferedImage image)
    {
        WritableRaster raster   = image.getRaster();
        DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
        return buffer.getData();
    }
    
    private static byte[] bit_conversion(int i){
        //originally integers (ints) cast into bytes
        //byte byte7 = (byte)((i & 0xFF00000000000000L) >>> 56);
        //byte byte6 = (byte)((i & 0x00FF000000000000L) >>> 48);
        //byte byte5 = (byte)((i & 0x0000FF0000000000L) >>> 40);
        //byte byte4 = (byte)((i & 0x000000FF00000000L) >>> 32); 
        //only using 4 bytes
        byte byte3 = (byte)((i & 0xFF000000) >>> 24); //0
        byte byte2 = (byte)((i & 0x00FF0000) >>> 16); //0
        byte byte1 = (byte)((i & 0x0000FF00) >>> 8 ); //0
        byte byte0 = (byte)((i & 0x000000FF)       );
        
        return(new byte[]{byte3,byte2,byte1,byte0});
    }

    static BufferedImage add_text(BufferedImage image, String text)
    {
        //convert all items to byte arrays: image, message, message length

        byte img[]  = get_byte_data(image);
        byte msg[] = text.getBytes();
        byte len[]   = bit_conversion(msg.length);
        try
        {
            encode_text(img, len,  0); //0 first position
            encode_text(img, msg, 32); //4 bytes of space for length: 4bytes*8bit = 32 bits
            succes=1;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Image File cannot Hold Message", "Error",JOptionPane.ERROR_MESSAGE);
            succes=0;
        }
        return image;
    }
    
    
    private static byte[] encode_text(byte[] image, byte[] addition, int offset){
        //check that the data + offset will fit in the image
        if(addition.length + offset > image.length)
        {
            throw new IllegalArgumentException("Image Dimension is to small!");
        }
        //loop through each addition byte
        for(int i=0; i<addition.length; ++i)
        {
            //loop through the 8 bits of each byte
            int add = addition[i];
            for(int bit=7; bit>=0; --bit, ++offset) //ensure the new offset value carries on through both loops
            {
                //assign an integer to b, shifted by bit spaces AND 1
                //a single bit of the current byte
                int b = (add >>> bit) & 1;
                //assign the bit by taking: [(previous byte value) AND 0xfe] OR bit to add
                //changes the last bit of the byte in the image to be the bit of addition
                image[offset] = (byte)((image[offset] & 0xFE) | b );
            }
        }
        return image;
    }
    
    private static byte[] decode_text(byte[] image)

    {
        int length = 0;
        int offset  = 32;
        //loop through 32 bytes of data to determine text length
        for(int i=0; i<32; ++i) //i=24 will also work, as only the 4th byte contains real data
        {
            length = (length << 1) | (image[i] & 1);
        }
         
        byte[] result = new byte[length];
        
        //loop through each byte of text

        for(int b=0; b<result.length; ++b )

        {
            //loop through each bit within a byte of text
            for(int i=0; i<8; ++i, ++offset)
            {
                //assign bit: [(new byte value) << 1] OR [(text byte) AND 1]
                result[b] = (byte)((result[b] << 1) | (image[offset] & 1));
            }
        }
        return result;
    }
    
    public static String decode(BufferedImage image)
    {
        byte[] decode;
        try
        {
            //user space is necessary for decrypting067
            decode = decode_text(get_byte_data(image));
            return(new String(decode));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                "There is no hidden message in this image!","Error",
                JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }

}



