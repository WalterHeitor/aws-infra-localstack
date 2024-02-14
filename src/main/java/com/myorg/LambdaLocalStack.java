package com.myorg;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.BucketEncryption;
import software.amazon.awscdk.services.s3.BucketProps;
import software.constructs.Construct;

public class LambdaLocalStack extends Stack {
    private Vpc vpc;
    public LambdaLocalStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public LambdaLocalStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

//        Bucket bucket = Bucket.Builder.create(this, "MyBucketWalterHeitor")
//                .versioned(true)
//                .encryption(BucketEncryption.KMS_MANAGED)
//                .build();

        Bucket bucket = new Bucket(this, "MyBucket_walter_heitor", new BucketProps.Builder()
                .encryption(BucketEncryption.KMS_MANAGED)
                .versioned(true) // Ative o versionamento do bucket
                .removalPolicy(RemovalPolicy.DESTROY) // A política de remoção (DESTROY exclui o bucket e todo o seu conteúdo quando a stack é excluída)
                .autoDeleteObjects(true) // Exclua objetos ao excluir o bucket
                .encryption(BucketEncryption.S3_MANAGED) // Criptografe os objetos do bucket com a chave gerenciada pelo S3

                .build());
    }

    public Vpc getVpc() {
        return vpc;
    }
}
