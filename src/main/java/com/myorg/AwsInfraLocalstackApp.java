package com.myorg;

import software.amazon.awscdk.App;

public final class AwsInfraLocalstackApp {
    public static void main(final String[] args) {
        App app = new App();
        // Crie e adicione a pilha S3LocalStack
        S3LocalStack s3LocalStack = new S3LocalStack(app, "S3LocalStack");

        // Crie e adicione a pilha LambdaLocalStack, passando o bucket S3 como argumento
        LambdaLocalStack lambdaLocalStack = new LambdaLocalStack(app, "LambdaLocalStack", null, s3LocalStack);

        lambdaLocalStack.addDependency(s3LocalStack);
        app.synth();
    }
}
