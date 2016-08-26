
/*
 * Gautam Mittal and Aaron Schultz
 * Nifty Stanford CS2: PeskyTourist
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

public class PeskyTourist {

	public static void main(String[] args) {

		List<BufferedImage> images = new ArrayList<BufferedImage>();

		for (int i = 1; i <= 9; i++) {
			try {

				BufferedImage originalImage = ImageIO
						.read(new File(System.getProperty("user.dir") + "/src/Pesky Tourist Images/" + i + ".png"));

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
				List<Integer> RGB = new ArrayList<Integer>();
				int median;
				for (int i = 0; i < images.size(); i++) {
					RGB.add(images.get(i).getRGB(x, y));

				}
				Collections.sort(RGB);
				if (RGB.size() % 2 == 0) {
					median = (RGB.get((RGB.size() / 2)) + RGB.get((RGB.size() / 2 - 1))) / 2;
				} else {
					median = RGB.get(RGB.size() / 2);
				}

				newImage.setRGB(x, y, median);
			}
		}

		// Display the final image
		File outputFile = new File(System.getProperty("user.dir") + "/src/Pesky Tourist Images/NO_TOURIST.png");
		try {
			ImageIO.write(newImage, "png", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
