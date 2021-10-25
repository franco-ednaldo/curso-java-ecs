package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.events.targets.SnsTopic;
import software.amazon.awscdk.services.sns.Topic;
import software.amazon.awscdk.services.sns.subscriptions.EmailSubscription;

public class SnsStack extends Stack {
	private final SnsTopic productEventsTopic;


	public SnsStack(final Construct scope, final String id) {
		this(scope, id, null);

	}

	public SnsStack(final Construct scope, final String id, final StackProps props) {
		super(scope, id, props);
		this.productEventsTopic = SnsTopic.Builder.create(Topic.Builder.create(this, "ProductEventsTopic")
						.topicName("product-events")
						.build())
				.build();

		this.productEventsTopic.getTopic().addSubscription(EmailSubscription.Builder.create("francoedanldo.gyn@gmial.com")
						.json(true)
				.build());

	}

	public SnsTopic getProductEventsTopic() {
		return productEventsTopic;
	}
}
