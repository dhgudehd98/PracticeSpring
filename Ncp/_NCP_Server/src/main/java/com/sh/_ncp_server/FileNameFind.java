package com.sh._ncp_server;

import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

import java.net.URL;
import java.util.Date;

public class FileNameFind {
    public static void main(String[] args) {
        final String endPoint = "https://kr.object.ncloudstorage.com";
        final String regionName = "kr-standard";
        final String accessKey = "ncp_iam_BPASKR1pd5uElFPzv8hI";
        final String secretKey = "ncp_iam_BPKSKRElH5DqBGp5Rx33PxHvyCR6HHkxYK";
        final String bucketName = "up-bucket";
        final String objectName = "Package/패키지2.png"; // 조회할 객체 이름

        // S3 클라이언트
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        try {
            // 객체 URL 생성
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectName)
                    .withMethod(HttpMethod.GET)
                    .withExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)); // URL 유효 기간 설정 (1시간)

            URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
            System.out.println(url.toString());
        } catch (AmazonS3Exception e) {
            System.err.println("Error occurred: " + e.getErrorMessage());
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

    }
}