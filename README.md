# myshop-dialogflow-webhook

basic dialogflow webhook to call external api.


# Running My Shop
```bash

#Set default user credentials
$ export SPRING_SECURITY_USER_NAME=<?>
$ export SPRING_SECURITY_USER_PASSWORD=<?>

#set my shop service url
$ export MYSHOP_SERVICE_URL=<?>

#run webhook
$ ../mvnw package
$ java -jar target/*.jar

```

# Testing My Shop

```bash
#edit fulfillment_request.json file and set user access token 
$ export TEST_USER_ACCESS_TOKEN=<?>
$ sed -i "s@<user_access_token_goes_here>@$TEST_USER_ACCESS_TOKEN@" fulfillement_request.json


#configuration can be validated using
$curl -i -X POST -H "Content-Type: application/json" \
--data-binary "@fulfillement_request.json" \
-H "Authorization: Basic dXNlcjpwYXNzd29yZA==" \
http://localhost:8082/webhook
```
