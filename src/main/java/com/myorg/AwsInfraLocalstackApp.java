package com.myorg;

import software.amazon.awscdk.App;

public final class AwsInfraLocalstackApp {
    public static void main(final String[] args) {
        App app = new App();

        new AwsInfraLocalstackStack(app, "AwsInfraLocalstackStack");

        app.synth();
    }
}
