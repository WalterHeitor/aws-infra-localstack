package com.myorg;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.lambda.eventsources.S3EventSource;
import software.amazon.awscdk.services.logs.LogGroup;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.EventType;
import software.constructs.Construct;

import java.util.List;
import java.util.Map;

public class LambdaLocalStack extends Stack {

    // Defina o nome do bucket S3 como uma constante
    private static final String BUCKET_NAME = "MyBucket_walter_heitor";

    public LambdaLocalStack(final Construct parent, final String id) {
        this(parent, id, null, null);
    }

    public LambdaLocalStack(final Construct parent, final String id, final StackProps props, S3LocalStack s3LocalStack) {
        super(parent, id, props);

        // Obtenha a referência do evento S3 da pilha S3LocalStack
        S3EventSource s3EventSource = s3LocalStack.getS3EventSource();

        // Crie um grupo de logs para a função Lambda
        LogGroup logGroup = LogGroup.Builder.create(this, "LambdaLogGroup")
                .logGroupName("MyLambdaLogGroup") // Nome do grupo de logs
                .removalPolicy(RemovalPolicy.DESTROY) // Política de remoção
                .build();

        // Crie uma função Lambda
        Function lambdaFunction = Function.Builder.create(this, "MyLambdaFunction")
                .runtime(Runtime.JAVA_17)
                .handler("helloworld.App::handleRequest") // substitua com o pacote e a classe de sua função Lambda
                .code(Code.fromAsset("/home/walter/Documentos/aws-infra-localstack/src/main/java/com/myorg/target/HelloWorld-1.0.jar")) // substitua com o caminho para o seu arquivo JAR
                .environment(Map.of(
                        "BUCKET_NAME", BUCKET_NAME
                ))
                .logGroup(logGroup) // Associe o grupo de logs à função Lambda
                .build();

        // Vincule o evento S3 à função Lambda
//        s3EventSource.bind(lambdaFunction);

        s3LocalStack.getBucket().grantRead(lambdaFunction);


    }

}
