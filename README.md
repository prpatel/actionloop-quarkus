<!--
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
-->
# ActionLoop Implementation for Quarkus

This is an OpenWhisk ActionLoop implementation for Quarkus. It implements a container that bundles Quarkus 1.4.2 libraries and allows easy deployment of Quarkus applications for OpenWhisk. Some notes:

* The Quarkus application is using Command Mode - not a standard http Quarkus application (lower overhead, memory usage, and faster startup time)
* Jackson is the JSON helper lib utilized

To get started:

`cd quarkus`

`mvn package`

`ibmcloud fn action create QCLITest1 target/quarkus-openwhisk-action-1.0.0-SNAPSHOT-runner.jar --docker actionloop-quarkus-1.4.2:latest`



