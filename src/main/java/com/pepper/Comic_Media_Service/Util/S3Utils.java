package com.pepper.Comic_Media_Service.Util;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.utils.Validate;

public class S3Utils {
     public static boolean doesBucketExist(String bucketName, S3Client s3Client) {
        try {
            Validate.notEmpty(bucketName, "The bucket name must not be null or an empty string.", "");
            s3Client.getBucketAcl(b -> b.bucket(bucketName));
            return true;
        } catch (AwsServiceException e) {
            // A redirect error or an AccessDenied exception means the bucket exists but it's not in this region
            // or we don't have permissions to it.
            if ((e.statusCode() == HttpStatusCode.MOVED_PERMANENTLY) || "AccessDenied".equals(e.awsErrorDetails().errorCode())) {
                return true;
            }
            if (e.statusCode() == HttpStatusCode.NOT_FOUND) {
                return false;
            }
            throw e;
        }
    }
}
