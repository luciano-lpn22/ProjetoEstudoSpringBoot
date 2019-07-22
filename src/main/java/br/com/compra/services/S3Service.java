package br.com.compra.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multPart) {
		try {
			String fileName = multPart.getOriginalFilename();
			InputStream is = multPart.getInputStream();
			String contentType = multPart.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			LOG.info("InputStream ->:" + e.getMessage());
			throw new RuntimeException("Erro no InputStream");
		}

	}

	private URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			LOG.info("Iniciando Upload");
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(contentType);
			s3Client.putObject(bucketName, fileName, is, objectMetadata);
			LOG.info("Fim Upload");
			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			LOG.info("AmazonServiceException ->:" + e.getMessage());
			throw new RuntimeException("Erro na conversão de URL para URL");
		}

	}
}
