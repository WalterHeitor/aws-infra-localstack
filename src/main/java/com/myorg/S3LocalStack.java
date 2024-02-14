package com.myorg;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.constructs.Construct;

public class S3LocalStack extends Stack {
    private Vpc vpc;
    public S3LocalStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public S3LocalStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        vpc = Vpc.Builder.create(this, "AluraVpc")
                .maxAzs(3)  // Default is all AZs in region
                .build();
    }

    public Vpc getVpc() {
        return vpc;
    }
}
