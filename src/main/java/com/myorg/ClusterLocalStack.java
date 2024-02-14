package com.myorg;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ecs.Cluster;
import software.constructs.Construct;

public class ClusterLocalStack extends Stack {
    public ClusterLocalStack(final Construct parent, final String id, final Vpc vpc) {
        this(parent, id, null, vpc);
    }

    public ClusterLocalStack(final Construct parent, final String id, final StackProps props, final Vpc vpc) {
        super(parent, id, props);

        Cluster cluster = Cluster.Builder.create(this, "ClusterLocalStack")
                .clusterName("cluste-local-stack")
                .vpc(vpc).build();
    }
}
