package com.myorg;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.lambda.eventsources.S3EventSource;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.BucketEncryption;
import software.amazon.awscdk.services.s3.BucketProps;
import software.amazon.awscdk.services.s3.EventType;
import software.constructs.Construct;

import java.util.List;

public class S3LocalStack extends Stack {
    public S3LocalStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    private final Bucket bucket;
    private final S3EventSource s3EventSource;
    public S3LocalStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        Bucket bucket1 = new Bucket(this, "MyBucket", new BucketProps.Builder()
                .versioned(true)
                .encryption(BucketEncryption.KMS_MANAGED)
                .removalPolicy(RemovalPolicy.DESTROY)
                .autoDeleteObjects(true)
                .build());

        BucketProps bucketProps = BucketProps.builder()
                .encryption(BucketEncryption.KMS_MANAGED)
                .versioned(true)
                .removalPolicy(RemovalPolicy.DESTROY)
                .autoDeleteObjects(true)
                .build();

        bucket = new Bucket(this, "MyBucket_walter_heitor", bucketProps);
        // Crie o evento S3
//        s3EventSource = S3EventSource.Builder.create(bucket)
//                .events(List.of(EventType.OBJECT_CREATED))
//                .build();
        s3EventSource = S3EventSource.Builder.create(bucket1)
                .events(List.of(EventType.OBJECT_CREATED))
                .build();
    }

    public Bucket getBucket() {
        return bucket;
    }
    // Retorne a referência do evento S3
    public S3EventSource getS3EventSource() {
        return s3EventSource;
    }
}
