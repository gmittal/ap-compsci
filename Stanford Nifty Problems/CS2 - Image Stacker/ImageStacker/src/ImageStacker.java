
/*
 * Aaron Schultz and Gautam Mittal
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageStacker {

	public static int meanList(List<Integer> l) {
		int sumList = 0;
		for (int i = 0; i < l.size(); i++) {
			sumList += l.get(i);
		}

		return sumList / l.size();
	}

	public static void main(String[] args) {

		List<BufferedImage> images = new ArrayList<BufferedImage>();

		for (int i = 1; i <= 10; i++) {
			try {

				BufferedImage originalImage = ImageIO
						.read(new File(System.getProperty("user.dir") + "/src/Noisy Images/" + i + ".png"));

				images.add(originalImage);

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		// Generate a new BufferedImage without the tourist
		BufferedImage newImage = new BufferedImage(images.get(0).getWidth(), images.get(0).getHeight(),
				BufferedImage.TYPE_INT_RGB);

		// Apply median filter to other images that do contain the pesky tourist
		for (int x = 0; x < images.get(0).getWidth(); x++) {
			for (int y = 0; y < images.get(0).getHeight(); y++) {
				List<Integer> red = new ArrayList<Integer>();
				List<Integer> green = new ArrayList<Integer>();
				List<Integer> blue = new ArrayList<Integer>();

				for (int i = 0; i < images.size(); i++) {
					int argb = images.get(i).getRGB(x, y);
					int r = (argb >> 16) & 0xFF;
					int g = (argb >> 8) & 0xFF;
					int b = (argb >> 0) & 0xFF;

					red.add(r);
					green.add(g);
					blue.add(b);
				}

				int finalR = meanList(red);
				int finalG = meanList(green);
				int finalB = meanList(blue);

				int rgb = ((finalR & 0x0ff) << 16) | ((finalG & 0x0ff) << 8) | (finalB & 0x0ff);

				newImage.setRGB(x, y, rgb);

			}
		}

		// Display the final image
		File outputFile = new File(System.getProperty("user.dir") + "/src/Noisy Images/NO_NOISE.png");
		try {
			ImageIO.write(newImage, "png", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
