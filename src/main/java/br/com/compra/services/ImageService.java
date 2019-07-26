package br.com.compra.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.compra.services.exceptions.FileException;

@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile  multipartFile) {
		String ext= FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		if (!ext.equals("jpg") && !ext.equals("png")) {
			throw new FileException("Somente imagem PNG ou JPG são permitidas");
		}
		try {
			BufferedImage  img=ImageIO.read(multipartFile.getInputStream());
			if (ext.equals("png")) {
				img=pngToJpj(img);
				
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao Ler arquivo");
		}
		
	}

	public BufferedImage pngToJpj(BufferedImage img) {
		BufferedImage bufferedImage= new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
		bufferedImage.createGraphics().drawImage(img,0,0,Color.WHITE,null);
		return bufferedImage;
	}
	
	public InputStream getInputStream(BufferedImage img,String ext) {
		try {
			ByteArrayOutputStream os= new ByteArrayOutputStream();
			ImageIO.write(img, ext, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}
}
