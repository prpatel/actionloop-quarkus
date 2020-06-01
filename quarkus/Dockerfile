#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
#FROM openwhisk/actionloop-v2:latest as builder
FROM adoptopenjdk/openjdk11-openj9:x86_64-ubuntu-jdk-11.0.7_10_openj9-0.20.0-slim
#FROM adoptopenjdk/openjdk11:x86_64-ubuntu-jdk-11.0.7_10-slim

RUN rm -rf /var/lib/apt/lists/* && apt-get clean && apt-get update \
	&& apt-get install -y --no-install-recommends locales python3 vim \
	&& rm -rf /var/lib/apt/lists/* \
	&& locale-gen en_US.UTF-8

ENV LANG="en_US.UTF-8" \
	LANGUAGE="en_US:en" \
	LC_ALL="en_US.UTF-8" \
	VERSION=8 \
	UPDATE=222 \
	BUILD=10

RUN mkdir -p /proxy/bin /proxy/lib /proxy/action
WORKDIR /proxy
#COPY --from=builder bin/proxy /bin/proxy

# This is the actionloop proxy built from source, version 1.16
ADD proxy_source /bin/proxy

ADD bin/compile /proxy/bin/compile
ENV OW_COMPILER=/proxy/bin/compile

ADD target/lib /app/lib
ADD bin/run.sh /proxy/bin/run.sh
ADD bin/run-bash.sh /proxy/bin/run-bash.sh

# for testing
#ADD target/quarkus-command-mode-1.0.0-SNAPSHOT-runner.jar /app/application.jar

#ENV OW_COMPILER=/proxy/bin/run.sh
#ENV OW_COMPILER=java -classpath=/app/application.jar:/app/lib/* org.openwhisk.Main
ENV OW_SAVE_JAR=application.jar
ENV OW_WAIT_FOR_ACK=1
ENV OW_LOG_INIT_ERROR=1
ENTRYPOINT ["/bin/proxy", "-debug"]
