package Binary;
import java.awt.image.BufferedImage; 
import java.io.File;  
import java.io.IOException;

import javax.imageio.ImageIO;
public class ToChangeBinary {
	 public static void main(String[] args) throws IOException {
		 BufferedImage desimg;
		 BufferedImage srcimg=ImageIO.read(new File("e:\\ce2.jpg"));
		 BinaryFilter Filter = new BinaryFilter();
		 desimg=Filter.filter(srcimg,null);
		 ImageIO.write(desimg, "jpg", new File("d:\\ce4.jpg"));
	}
}
