Cake Manager Micro Service (fictitious)
=======================================

Requirements:
* By accessing the root of the server (/) it should be possible to list the cakes currently in the system. This must be presented in an acceptable format for a human to read.

* It must be possible for a human to add a new cake to the server.

* By accessing an alternative endpoint (/cakes) with an appropriate client it must be possible to download a list of
  the cakes currently in the system as JSON data.

* The /cakes endpoint must also allow new cakes to be created.

JWT Enabled.
For accessing all resources mentioned above we have to generate JWT token first
for generating JWT token start application
For starting that you need to pass runtime parameter

-Djasypt.encryptor.password=test

For generating token execute following resource
Post /jwt-token with body
{ 
  "userName": "user1",
  "password": "pwd1"
}
****Cucumber Test:
Present in package com.waracle.cakemgr.cucumber
